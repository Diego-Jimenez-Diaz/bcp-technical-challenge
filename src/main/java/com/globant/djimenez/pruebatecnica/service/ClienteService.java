package com.globant.djimenez.pruebatecnica.service;

import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;

import java.util.Map;

public interface ClienteService {
    ClienteDTOResponse createCliente(ClienteDTORequest clienteDTORequest);
    ClienteDTOResponse updateCliente(Long id, ClienteDTORequest clienteDTORequest);
    ClienteDTOResponse updatePartialCliente(Long id, Map<String, Object> fields);
    void deleteCliente(Long id);
    ClienteDTOResponse getClienteById(Long id);
}
