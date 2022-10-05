package com.globant.djimenez.pruebatecnica.mapper;

import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteDTOResponse clienteToClienteDTOResponse(Cliente cliente){
        return ClienteDTOResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .genero(cliente.getGenero())
                .edad(cliente.getEdad())
                .identificacion(cliente.getIdentificacion())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .clienteId(cliente.getClienteId())
                .contrasena(cliente.getContrasena())
                .estado(cliente.getEstado())
                .build();
    }

    public Cliente clienteDTORequestToCliente(ClienteDTORequest clienteDTORequest){
        Cliente cliente = new Cliente();
        cliente.setId(null);
        cliente.setNombre(clienteDTORequest.getNombre());
        cliente.setGenero(clienteDTORequest.getGenero());
        cliente.setEdad(clienteDTORequest.getEdad());
        cliente.setIdentificacion(clienteDTORequest.getIdentificacion());
        cliente.setDireccion(clienteDTORequest.getDireccion());
        cliente.setTelefono(clienteDTORequest.getTelefono());
        cliente.setClienteId(clienteDTORequest.getClienteId());
        cliente.setContrasena(clienteDTORequest.getContrasena());
        cliente.setEstado(clienteDTORequest.getEstado());

        return cliente;
    }

    public Cliente clienteDTORequestToCliente(Long id, ClienteDTORequest clienteDTORequest){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(clienteDTORequest.getNombre());
        cliente.setGenero(clienteDTORequest.getGenero());
        cliente.setEdad(clienteDTORequest.getEdad());
        cliente.setIdentificacion(clienteDTORequest.getIdentificacion());
        cliente.setDireccion(clienteDTORequest.getDireccion());
        cliente.setTelefono(clienteDTORequest.getTelefono());
        cliente.setClienteId(clienteDTORequest.getClienteId());
        cliente.setContrasena(clienteDTORequest.getContrasena());
        cliente.setEstado(clienteDTORequest.getEstado());

        return cliente;
    }

    public Cliente clienteDTOResponseToCliente(ClienteDTOResponse clienteDTOResponse){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTOResponse.getId());
        cliente.setNombre(clienteDTOResponse.getNombre());
        cliente.setGenero(clienteDTOResponse.getGenero());
        cliente.setEdad(clienteDTOResponse.getEdad());
        cliente.setIdentificacion(clienteDTOResponse.getIdentificacion());
        cliente.setDireccion(clienteDTOResponse.getDireccion());
        cliente.setTelefono(clienteDTOResponse.getTelefono());
        cliente.setClienteId(clienteDTOResponse.getClienteId());
        cliente.setContrasena(clienteDTOResponse.getContrasena());
        cliente.setEstado(clienteDTOResponse.getEstado());

        return cliente;
    }

}
