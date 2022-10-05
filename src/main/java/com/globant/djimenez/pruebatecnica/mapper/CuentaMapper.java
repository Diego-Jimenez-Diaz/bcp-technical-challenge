package com.globant.djimenez.pruebatecnica.mapper;

import com.globant.djimenez.pruebatecnica.dto.request.CuentaDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.CuentaDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cuenta;
import com.globant.djimenez.pruebatecnica.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {
    public CuentaDTOResponse cuentaToCuentaDTOResponse(Cuenta cuenta){
        return CuentaDTOResponse.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .clienteId(cuenta.getCliente().getId())
                .build();
    }

    public Cuenta cuentaDTORequestToCuenta(CuentaDTORequest cuentaDTORequest){
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDTORequest.getClienteId());

        Cuenta cuenta = new Cuenta();
        cuenta.setId(null);
        cuenta.setNumeroCuenta(cuentaDTORequest.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTORequest.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTORequest.getSaldoInicial());
        cuenta.setEstado(cuentaDTORequest.getEstado());
        cuenta.setCliente(cliente);

        return cuenta;
    }

    public Cuenta cuentaDTORequestToCuenta(Long id, CuentaDTORequest cuentaDTORequest){
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDTORequest.getClienteId());

        Cuenta cuenta = new Cuenta();
        cuenta.setId(id);
        cuenta.setNumeroCuenta(cuentaDTORequest.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTORequest.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTORequest.getSaldoInicial());
        cuenta.setEstado(cuentaDTORequest.getEstado());
        cuenta.setCliente(cliente);

        return cuenta;
    }

    public Cuenta cuentaDTOResponseToCuenta(CuentaDTOResponse cuentaDTOResponse){
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDTOResponse.getClienteId());

        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDTOResponse.getId());
        cuenta.setNumeroCuenta(cuentaDTOResponse.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTOResponse.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTOResponse.getSaldoInicial());
        cuenta.setEstado(cuentaDTOResponse.getEstado());
        cuenta.setCliente(cliente);

        return cuenta;
    }
}
