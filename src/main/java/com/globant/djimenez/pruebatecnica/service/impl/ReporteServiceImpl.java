package com.globant.djimenez.pruebatecnica.service.impl;

import com.globant.djimenez.pruebatecnica.dao.CuentaDAO;
import com.globant.djimenez.pruebatecnica.dao.MovimientoDAO;
import com.globant.djimenez.pruebatecnica.dto.response.ReporteDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cuenta;
import com.globant.djimenez.pruebatecnica.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReportesService {

    @Autowired
    CuentaDAO cuentaDAO;

    @Autowired
    MovimientoDAO movimientoDAO;

    @Override
    public List<ReporteDTOResponse> getReportByDataRange(Long customerId, LocalDate from, LocalDate to) {
        List<ReporteDTOResponse> reporteDTOResponseList = new ArrayList<>();
        List<Cuenta> accountList = cuentaDAO.getAccountsBycustomerId(customerId);
        accountList.forEach(cuenta ->
            movimientoDAO.getMovimientosByCuentaIdAndDateRange(cuenta.getId(), from, to).forEach(movimiento ->
                reporteDTOResponseList.add(ReporteDTOResponse.builder()
                                .fecha(movimiento.getFecha())
                                .cliente(cuenta.getCliente().getNombre())
                                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                                .tipo(movimiento.getTipoMovimiento())
                                .saldoInicial(movimiento.getSaldoInicial())
                                .estado(movimiento.getEstado())
                                .movimiento(movimiento.getValor())
                                .saldoDisponible(movimiento.getSaldoInicial() + movimiento.getValor())
                        .build())
            )
        );
        return reporteDTOResponseList;
    }
}
