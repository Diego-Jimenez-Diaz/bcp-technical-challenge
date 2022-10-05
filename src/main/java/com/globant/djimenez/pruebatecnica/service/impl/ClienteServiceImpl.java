package com.globant.djimenez.pruebatecnica.service.impl;

import com.globant.djimenez.pruebatecnica.dao.ClienteDAO;
import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cliente;
import com.globant.djimenez.pruebatecnica.exception.InvalidOperationException;
import com.globant.djimenez.pruebatecnica.exception.ResourceNotFoundException;
import com.globant.djimenez.pruebatecnica.mapper.ClienteMapper;
import com.globant.djimenez.pruebatecnica.service.ClienteService;
import com.globant.djimenez.pruebatecnica.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDAO clienteDAO;

    @Autowired
    ClienteMapper clienteMapper;
    @Override
    public ClienteDTOResponse createCliente(ClienteDTORequest clienteDTORequest) {
        return clienteMapper.clienteToClienteDTOResponse(clienteDAO.save(clienteMapper.clienteDTORequestToCliente(clienteDTORequest)));
    }

    @Override
    public ClienteDTOResponse getClienteById(Long id){
        Optional<Cliente> clienteOptional = clienteDAO.findById(id);
        if(!clienteOptional.isPresent()){
            throw new ResourceNotFoundException(Constant.CUSTOMER_ID_NOT_FOUND_EXCEPTION);
        }
        return clienteMapper.clienteToClienteDTOResponse(clienteOptional.get());
    }

    @Override
    public ClienteDTOResponse updateCliente(Long id, ClienteDTORequest clienteDTORequest) {
        if(!clienteDAO.existsById(id)){
            throw new ResourceNotFoundException(Constant.CUSTOMER_ID_NOT_FOUND_EXCEPTION);
        }
        return clienteMapper.clienteToClienteDTOResponse(clienteDAO.save(clienteMapper.clienteDTORequestToCliente(id, clienteDTORequest)));
    }

    @Override
    public ClienteDTOResponse updatePartialCliente(Long id, Map<String, Object>  fields) {
        ClienteDTOResponse clienteDTOResponse = getClienteById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ClienteDTOResponse.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, clienteDTOResponse, value);
        });
        return clienteMapper.clienteToClienteDTOResponse(clienteDAO.save(clienteMapper.clienteDTOResponseToCliente(clienteDTOResponse)));
    }

    @Override
    public void deleteCliente(Long id) {
        ClienteDTOResponse clienteDTOResponse = getClienteById(id);
        if (Boolean.FALSE.equals(clienteDTOResponse.getEstado())){
            throw new InvalidOperationException(Constant.CUSTOMER_ALREADY_DELETED);
        }
        clienteDTOResponse.setEstado(Boolean.FALSE);
        clienteDAO.save(clienteMapper.clienteDTOResponseToCliente(clienteDTOResponse));
    }
}
