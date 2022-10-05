package com.globant.djimenez.pruebatecnica.mapper;

import com.globant.djimenez.pruebatecnica.dto.request.MovimientoDTORequest;
import com.globant.djimenez.pruebatecnica.dto.request.MovimientoUpdateDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.MovimientoDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cuenta;
import com.globant.djimenez.pruebatecnica.entity.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {
    public Movimiento movimientoDTORequestToMovimiento(MovimientoDTORequest movimientoDTORequest, Double totalBalance){
        Cuenta cuenta = new Cuenta();
        cuenta.setId(movimientoDTORequest.getCuentaId());

        Movimiento movimiento = new Movimiento();
        movimiento.setId(null);
        movimiento.setFecha(movimientoDTORequest.getFecha());
        movimiento.setTipoMovimiento(movimientoDTORequest.getTipoMovimiento());
        movimiento.setSaldoInicial(totalBalance);
        movimiento.setValor(movimientoDTORequest.getValor());
        movimiento.setEstado(movimientoDTORequest.getEstado());
        movimiento.setCuenta(cuenta);

        return movimiento;
    }

    public Movimiento movimientoDTOResponseToMovimiento(MovimientoDTOResponse movimientoDTOResponse){
        Cuenta cuenta = new Cuenta();
        cuenta.setId(movimientoDTOResponse.getCuentaId());

        Movimiento movimiento = new Movimiento();
        movimiento.setId(movimientoDTOResponse.getId());
        movimiento.setFecha(movimientoDTOResponse.getFecha());
        movimiento.setTipoMovimiento(movimientoDTOResponse.getTipoMovimiento());
        movimiento.setSaldoInicial(movimientoDTOResponse.getSaldoInicial());
        movimiento.setValor(movimientoDTOResponse.getValor());
        movimiento.setEstado(movimientoDTOResponse.getEstado());
        movimiento.setCuenta(cuenta);

        return movimiento;
    }

    public Movimiento movimientoUpdateDTORequestToMovimiento(Long id, MovimientoUpdateDTORequest movimientoUpdateDTORequest){
        Cuenta cuenta = new Cuenta();
        cuenta.setId(movimientoUpdateDTORequest.getCuentaId());

        Movimiento movimiento = new Movimiento();
        movimiento.setId(id);
        movimiento.setFecha(movimientoUpdateDTORequest.getFecha());
        movimiento.setTipoMovimiento(movimientoUpdateDTORequest.getTipoMovimiento());
        movimiento.setSaldoInicial(movimientoUpdateDTORequest.getSaldoInicial());
        movimiento.setValor(movimientoUpdateDTORequest.getValor());
        movimiento.setEstado(movimientoUpdateDTORequest.getEstado());
        movimiento.setCuenta(cuenta);

        return movimiento;
    }

    public MovimientoDTOResponse movimientoToMovimientoDTOResponse(Movimiento movimiento){
        return MovimientoDTOResponse.builder()
                .id(movimiento.getId())
                .fecha(movimiento.getFecha())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .saldoInicial(movimiento.getSaldoInicial())
                .valor(movimiento.getValor())
                .estado(movimiento.getEstado())
                .cuentaId(movimiento.getCuenta().getId())
                .build();
    }
}
