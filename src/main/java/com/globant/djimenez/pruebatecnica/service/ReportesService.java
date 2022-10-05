package com.globant.djimenez.pruebatecnica.service;

import com.globant.djimenez.pruebatecnica.dto.response.ReporteDTOResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReportesService {
    List<ReporteDTOResponse> getReportByDataRange(Long customerId, LocalDate from, LocalDate to);
}
