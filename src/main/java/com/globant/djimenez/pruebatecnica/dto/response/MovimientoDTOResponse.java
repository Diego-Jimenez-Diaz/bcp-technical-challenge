package com.globant.djimenez.pruebatecnica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTOResponse {

    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double saldoInicial;
    private Double valor;
    private Boolean estado;
    private Long cuentaId;

}
