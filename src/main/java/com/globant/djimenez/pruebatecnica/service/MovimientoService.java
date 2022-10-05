package com.globant.djimenez.pruebatecnica.service;


import com.globant.djimenez.pruebatecnica.dto.request.MovimientoDTORequest;
import com.globant.djimenez.pruebatecnica.dto.request.MovimientoUpdateDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.MovimientoDTOResponse;

import java.util.Map;

public interface MovimientoService {
    MovimientoDTOResponse createMovimiento(MovimientoDTORequest movimientoDTORequest);
    MovimientoDTOResponse getMovimientoById(Long id);
    MovimientoDTOResponse updateMovimiento(Long id, MovimientoUpdateDTORequest movimientoUpdateDTORequest);
    MovimientoDTOResponse updatePartialMovimiento(Long id, Map<String, Object> fields);
    void deleteMovimiento(Long id);
}
