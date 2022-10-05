package com.globant.djimenez.pruebatecnica.service;

import com.globant.djimenez.pruebatecnica.dto.request.CuentaDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.CuentaDTOResponse;

import java.util.Map;

public interface CuentaService {
    CuentaDTOResponse createCuenta(CuentaDTORequest cuentaDTORequest);
    CuentaDTOResponse updateCuenta(Long id, CuentaDTORequest cuentaDTORequest);
    CuentaDTOResponse updatePartialCuenta(Long id, Map<String, Object> fields);
    void deleteCuenta(Long id);
    CuentaDTOResponse getCuentaById(Long id);

}
