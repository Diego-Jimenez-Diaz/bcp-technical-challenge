package com.globant.djimenez.pruebatecnica.controller;

import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;
import com.globant.djimenez.pruebatecnica.service.ClienteService;
import com.globant.djimenez.pruebatecnica.util.TestConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @Test
    public void createClienteHappyPathTest(){
        ClienteDTORequest clienteDTORequest = createClienteDTORequest();
        ClienteDTOResponse clienteDTOResponse = createClienteDTOResponse(BigDecimal.ONE.longValue());

        when(clienteService.createCliente(clienteDTORequest)).thenReturn(clienteDTOResponse);

        ResponseEntity<ClienteDTOResponse> response = clienteController.createCliente(clienteDTORequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteDTOResponse, response.getBody());
        verify(clienteService, times(BigDecimal.ONE.intValue())).createCliente(clienteDTORequest);
    }

    @Test
    public void getClienteByIdHappyPathTest(){
        Long id = BigDecimal.ONE.longValue();
        ClienteDTOResponse clienteDTOResponse = createClienteDTOResponse(BigDecimal.ONE.longValue());

        when(clienteService.getClienteById(id)).thenReturn(clienteDTOResponse);

        ResponseEntity<ClienteDTOResponse> response = clienteController.getClienteById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        verify(clienteService, times(BigDecimal.ONE.intValue())).getClienteById(id);

    }

    private ClienteDTORequest createClienteDTORequest(){
        return ClienteDTORequest.builder()
                .nombre(TestConstant.NAME_TEST)
                .genero(TestConstant.GENDER)
                .edad(TestConstant.AGE)
                .identificacion(TestConstant.IDENTIFICATION)
                .direccion(TestConstant.ADDRESS_TEST)
                .telefono(TestConstant.PHONE_NUMBER_TEST)
                .clienteId(TestConstant.CUSTOMER_ID_TEST)
                .contrasena(TestConstant.PASSWORD_TEST)
                .estado(Boolean.TRUE)
                .build();
    }

    private ClienteDTOResponse createClienteDTOResponse(Long id){
        return ClienteDTOResponse.builder()
                .id(id)
                .nombre(TestConstant.NAME_TEST)
                .genero(TestConstant.GENDER)
                .edad(TestConstant.AGE)
                .identificacion(TestConstant.IDENTIFICATION)
                .direccion(TestConstant.ADDRESS_TEST)
                .telefono(TestConstant.PHONE_NUMBER_TEST)
                .clienteId(TestConstant.CUSTOMER_ID_TEST)
                .contrasena(TestConstant.PASSWORD_TEST)
                .estado(Boolean.TRUE)
                .build();
    }

}
