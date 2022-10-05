package com.globant.djimenez.pruebatecnica.service.impl;

import com.globant.djimenez.pruebatecnica.dao.CuentaDAO;
import com.globant.djimenez.pruebatecnica.dto.request.CuentaDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.CuentaDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cuenta;
import com.globant.djimenez.pruebatecnica.exception.InvalidOperationException;
import com.globant.djimenez.pruebatecnica.exception.ResourceNotFoundException;
import com.globant.djimenez.pruebatecnica.mapper.CuentaMapper;
import com.globant.djimenez.pruebatecnica.service.CuentaService;
import com.globant.djimenez.pruebatecnica.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    CuentaDAO cuentaDAO;

    @Autowired
    CuentaMapper cuentaMapper;

    @Override
    public CuentaDTOResponse createCuenta(CuentaDTORequest cuentaDTORequest) {
        if(cuentaDAO.existsById(cuentaDTORequest.getNumeroCuenta())){
            throw new InvalidOperationException(Constant.ACCOUNT_NUMBER_EXISTING_EXCEPTION);
        }
        return cuentaMapper.cuentaToCuentaDTOResponse(cuentaDAO.save(cuentaMapper.cuentaDTORequestToCuenta(cuentaDTORequest)));
    }

    public CuentaDTOResponse getCuentaById(Long id){
        Optional<Cuenta> optionalAccount = cuentaDAO.findById(id);
        if (!optionalAccount.isPresent()){
            throw new ResourceNotFoundException(Constant.ACCOUNT_ID_NOT_FOUND_EXCEPTION);
        }
        return cuentaMapper.cuentaToCuentaDTOResponse(optionalAccount.get());
    }

    @Override
    public CuentaDTOResponse updateCuenta(Long id, CuentaDTORequest cuentaDTORequest) {
        if(!cuentaDAO.existsById(id)){
            throw new ResourceNotFoundException(Constant.ACCOUNT_ID_NOT_FOUND_EXCEPTION);
        }
        return cuentaMapper.cuentaToCuentaDTOResponse(cuentaDAO.save(cuentaMapper.cuentaDTORequestToCuenta(id, cuentaDTORequest)));
    }

    @Override
    public CuentaDTOResponse updatePartialCuenta(Long id, Map<String, Object> fields) {
        CuentaDTOResponse cuentaDTOResponse = getCuentaById(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CuentaDTOResponse.class, key);
            field.setAccessible(true);
            if(field.getType().equals(Long.class)) {
                ReflectionUtils.setField(field, cuentaDTOResponse, Long.valueOf(value.toString()));
            }else if (field.getType().equals(Double.class)){
                ReflectionUtils.setField(field, cuentaDTOResponse, Double.valueOf(value.toString()));
            }else{
                ReflectionUtils.setField(field, cuentaDTOResponse, value);
            }
        });

        return cuentaMapper.cuentaToCuentaDTOResponse(cuentaDAO.save(cuentaMapper.cuentaDTOResponseToCuenta(cuentaDTOResponse)));
    }

    @Override
    public void deleteCuenta(Long id) {
        CuentaDTOResponse cuentaDTOResponse = getCuentaById(id);
        if (Boolean.FALSE.equals(cuentaDTOResponse.getEstado())){
            throw new InvalidOperationException(Constant.ACCOUNT_ALREADY_DELETED);
        }
        cuentaDTOResponse.setEstado(Boolean.FALSE);
        cuentaDAO.save(cuentaMapper.cuentaDTOResponseToCuenta(cuentaDTOResponse));
    }
}
