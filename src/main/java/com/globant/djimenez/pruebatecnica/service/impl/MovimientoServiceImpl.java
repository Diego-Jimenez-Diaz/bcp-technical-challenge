package com.globant.djimenez.pruebatecnica.service.impl;

import com.globant.djimenez.pruebatecnica.dao.MovimientoDAO;
import com.globant.djimenez.pruebatecnica.dto.request.MovimientoDTORequest;
import com.globant.djimenez.pruebatecnica.dto.request.MovimientoUpdateDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.CuentaDTOResponse;
import com.globant.djimenez.pruebatecnica.dto.response.MovimientoDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Movimiento;
import com.globant.djimenez.pruebatecnica.exception.InvalidOperationException;
import com.globant.djimenez.pruebatecnica.exception.ResourceNotFoundException;
import com.globant.djimenez.pruebatecnica.exception.TransactionException;
import com.globant.djimenez.pruebatecnica.mapper.MovimientoMapper;
import com.globant.djimenez.pruebatecnica.service.CuentaService;
import com.globant.djimenez.pruebatecnica.service.MovimientoService;
import com.globant.djimenez.pruebatecnica.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientoDAO movimientoDAO;

    @Autowired
    MovimientoMapper movimientoMapper;

    @Override
    public MovimientoDTOResponse createMovimiento(MovimientoDTORequest movimientoDTORequest) {
        CuentaDTOResponse account = cuentaService.getCuentaById(movimientoDTORequest.getCuentaId());
        double subTotal = account.getSaldoInicial() + movimientoDTORequest.getValor();
        if (subTotal < 0) {
            throw new TransactionException(Constant.INSUFFICIENT_BALANCE_EXCEPTION);
        }

        Map<String, Object> accountMap = new HashMap<>();
        accountMap.put(Constant.MovimientoFields.INITIAL_BALANCE.getFieldName(), subTotal);
        cuentaService.updatePartialCuenta(account.getId(), accountMap);

        return movimientoMapper.movimientoToMovimientoDTOResponse(movimientoDAO.save(movimientoMapper.movimientoDTORequestToMovimiento(movimientoDTORequest, account.getSaldoInicial())));
    }

    @Override
    public MovimientoDTOResponse getMovimientoById(Long id){
        Optional<Movimiento> movimientoOptional = movimientoDAO.findById(id);
        if (!movimientoOptional.isPresent()){
            throw new ResourceNotFoundException(Constant.TRANSACTION_ID_NOT_FOUND_EXCEPTION);
        }
        return movimientoMapper.movimientoToMovimientoDTOResponse(movimientoOptional.get());
    }

    @Override
    public MovimientoDTOResponse updateMovimiento(Long id, MovimientoUpdateDTORequest movimientoUpdateDTORequest) {
        MovimientoDTOResponse currentMovimiento = getMovimientoById(id);

        if(Boolean.FALSE.equals(currentMovimiento.getEstado())){
            throw new InvalidOperationException(Constant.TRANSACTION_ALREADY_DELETED);
        }

        if(!movimientoUpdateDTORequest.getCuentaId().equals(currentMovimiento.getCuentaId()) || !movimientoUpdateDTORequest.getValor().equals(currentMovimiento.getValor())){
            adjustBalance(movimientoUpdateDTORequest.getCuentaId(), movimientoUpdateDTORequest.getValor(), currentMovimiento);
        }

        return movimientoMapper.movimientoToMovimientoDTOResponse(movimientoDAO.save(movimientoMapper.movimientoUpdateDTORequestToMovimiento(id, movimientoUpdateDTORequest)));
    }

    @Override
    public MovimientoDTOResponse updatePartialMovimiento(Long id, Map<String, Object> fields) {
        MovimientoDTOResponse newMovimiento = getMovimientoById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(MovimientoDTOResponse.class, key);
            field.setAccessible(true);
            if(field.getType().equals(LocalDate.class)){
                ReflectionUtils.setField(field, newMovimiento, LocalDate.parse(value.toString()));
            } else if (field.getType().equals(Long.class)) {
                ReflectionUtils.setField(field, newMovimiento, Long.valueOf(value.toString()));
            }else if(field.getType().equals(Double.class)){
                ReflectionUtils.setField(field, newMovimiento, Double.valueOf(value.toString()));
            }else{
                ReflectionUtils.setField(field, newMovimiento, value);
            }

        });

        if (fields.containsKey(Constant.MovimientoFields.ACCOUNT_ID.getFieldName()) || fields.containsKey(Constant.MovimientoFields.VALUE.getFieldName())){
            MovimientoDTOResponse currentMovimiento = getMovimientoById(id);
            adjustBalance(newMovimiento.getCuentaId(), newMovimiento.getValor(), currentMovimiento);
        }

        return movimientoMapper.movimientoToMovimientoDTOResponse(movimientoDAO.save(movimientoMapper.movimientoDTOResponseToMovimiento(newMovimiento)));
    }

    @Override
    public void deleteMovimiento(Long id) {
        MovimientoDTOResponse movimientoDTOResponse = getMovimientoById(id);
        if (Boolean.FALSE.equals(movimientoDTOResponse.getEstado())){
            throw new InvalidOperationException(Constant.TRANSACTION_ALREADY_DELETED);
        }

        CuentaDTOResponse account = cuentaService.getCuentaById(movimientoDTOResponse.getCuentaId());
        double subTotal = account.getSaldoInicial() + (movimientoDTOResponse.getValor() * -1);

        if(subTotal < 0){
            throw new TransactionException(Constant.INSUFFICIENT_BALANCE_EXCEPTION);
        }

        Map<String, Object> accountMap = new HashMap<>();
        accountMap.put(Constant.MovimientoFields.INITIAL_BALANCE.getFieldName(), subTotal);
        cuentaService.updatePartialCuenta(account.getId(), accountMap);

        movimientoDTOResponse.setEstado(Boolean.FALSE);
        movimientoDAO.save(movimientoMapper.movimientoDTOResponseToMovimiento(movimientoDTOResponse));
    }

    private void adjustBalance(Long updateCuentaId, Double updateValue, MovimientoDTOResponse currentMovimiento){
        CuentaDTOResponse account = cuentaService.getCuentaById(currentMovimiento.getCuentaId());


        if(updateCuentaId.equals(currentMovimiento.getCuentaId())){
            double currentValue = currentMovimiento.getValor();
            double difference = updateValue - currentValue;

            if((account.getSaldoInicial() + difference) < 0){
                throw new TransactionException(Constant.INSUFFICIENT_BALANCE_EXCEPTION);
            }

            Map<String, Object> accountMap = new HashMap<>();
            accountMap.put(Constant.MovimientoFields.INITIAL_BALANCE.getFieldName(), (account.getSaldoInicial() + difference));
            cuentaService.updatePartialCuenta(account.getId(), accountMap);
        }else{
            CuentaDTOResponse newAccount = cuentaService.getCuentaById(updateCuentaId);

            double adjustBalanceOldAccount = account.getSaldoInicial() + (currentMovimiento.getValor() * -1);
            double adjustBalanceNewAccount = newAccount.getSaldoInicial() + updateValue;
            if (adjustBalanceOldAccount < 0 || adjustBalanceNewAccount <0){
                throw new TransactionException(Constant.INSUFFICIENT_BALANCE_EXCEPTION);
            }

            Map<String, Object> accountMap = new HashMap<>();
            accountMap.put(Constant.MovimientoFields.INITIAL_BALANCE.getFieldName(), adjustBalanceOldAccount);
            cuentaService.updatePartialCuenta(account.getId(), accountMap);

            Map<String, Object> newAccountMap = new HashMap<>();
            newAccountMap.put(Constant.MovimientoFields.INITIAL_BALANCE.getFieldName(), adjustBalanceNewAccount);
            cuentaService.updatePartialCuenta(newAccount.getId(), newAccountMap);
        }
    }
}
