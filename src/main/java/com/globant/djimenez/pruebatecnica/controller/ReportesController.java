package com.globant.djimenez.pruebatecnica.controller;

import com.globant.djimenez.pruebatecnica.dto.response.ReporteDTOResponse;
import com.globant.djimenez.pruebatecnica.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/reportes")
@RestController
public class ReportesController {
    @Autowired
    ReportesService reportesService;

    @GetMapping
    public ResponseEntity<List<ReporteDTOResponse>> getReporteByDataRange(@RequestParam("from")String fromDate, @RequestParam("to")String toDate, @RequestHeader("clienteId") Long clienteId){
        return new ResponseEntity<>(reportesService.getReportByDataRange(clienteId, LocalDate.parse(fromDate), LocalDate.parse(toDate)), HttpStatus.OK);
    }
}
