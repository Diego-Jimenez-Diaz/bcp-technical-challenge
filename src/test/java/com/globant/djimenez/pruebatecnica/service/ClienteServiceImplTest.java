package com.globant.djimenez.pruebatecnica.service;

import com.globant.djimenez.pruebatecnica.dao.ClienteDAO;
import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;
import com.globant.djimenez.pruebatecnica.entity.Cliente;
import com.globant.djimenez.pruebatecnica.exception.ResourceNotFoundException;
import com.globant.djimenez.pruebatecnica.mapper.ClienteMapper;
import com.globant.djimenez.pruebatecnica.service.impl.ClienteServiceImpl;
import com.globant.djimenez.pruebatecnica.util.TestConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    ClienteMapper clienteMapper;

    @Mock
    ClienteDAO clienteDAO;

    @Before
    public void setUp(){
    }

    @Test
    public void createClienteHappyPathTest(){
        ClienteDTORequest clienteDTORequest = createClienteDTORequest();
        Cliente clienteIn = createCliente(null);
        Cliente clienteOut = createCliente(BigDecimal.ONE.longValue());
        ClienteDTOResponse clienteDTOResponse = createClienteDTOResponse(BigDecimal.ONE.longValue());

        when(clienteMapper.clienteDTORequestToCliente(clienteDTORequest)).thenReturn(clienteIn);
        when(clienteDAO.save(clienteIn)).thenReturn(clienteOut);
        when(clienteMapper.clienteToClienteDTOResponse(clienteOut)).thenReturn(clienteDTOResponse);

        ClienteDTOResponse response = clienteService.createCliente(clienteDTORequest);

        assertNotNull(response);
        assertEquals(Long.valueOf(BigDecimal.ONE.longValue()), response.getId());

        verify(clienteMapper, times(BigDecimal.ONE.intValue())).clienteDTORequestToCliente(clienteDTORequest);
        verify(clienteDAO, times(BigDecimal.ONE.intValue())).save(clienteIn);
        verify(clienteMapper, times(BigDecimal.ONE.intValue())).clienteToClienteDTOResponse(clienteOut);
    }

    @Test
    public void getClienteByIdHappyPathTest(){
        Long id = BigDecimal.ONE.longValue();
        Optional<Cliente> optionalCliente = Optional.of(createCliente(id));
        ClienteDTOResponse clienteDTOResponse = createClienteDTOResponse(id);

        when(clienteDAO.findById(id)).thenReturn(optionalCliente);
        when(clienteMapper.clienteToClienteDTOResponse(optionalCliente.get())).thenReturn(clienteDTOResponse);

        ClienteDTOResponse response = clienteService.getClienteById(id);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(TestConstant.NAME_TEST, response.getNombre());
        assertEquals(TestConstant.GENDER, response.getGenero());
        assertEquals(TestConstant.AGE, response.getEdad());
        assertEquals(TestConstant.IDENTIFICATION, response.getIdentificacion());
        assertEquals(TestConstant.ADDRESS_TEST, response.getDireccion());
        assertEquals(TestConstant.PHONE_NUMBER_TEST, response.getTelefono());
        assertEquals(TestConstant.CUSTOMER_ID_TEST, response.getClienteId());
        assertEquals(TestConstant.PASSWORD_TEST, response.getContrasena());
        assertEquals(TestConstant.STATUS_TEST, response.getEstado());
        verify(clienteDAO, times(BigDecimal.ONE.intValue())).findById(id);
        verify(clienteMapper, times(BigDecimal.ONE.intValue())).clienteToClienteDTOResponse(optionalCliente.get());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getClienteByIdNotFoundTest(){
        Long id = BigDecimal.TEN.longValue();
        Optional<Cliente> optionalCliente = Optional.empty();

        when(clienteDAO.findById(id)).thenReturn(optionalCliente);

        ClienteDTOResponse response = clienteService.getClienteById(id);
        verify(clienteDAO, times(BigDecimal.ONE.intValue())).findById(id);
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
                .estado(TestConstant.STATUS_TEST)
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
                .estado(TestConstant.STATUS_TEST)
                .build();
    }

    private Cliente createCliente(Long id){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(TestConstant.NAME_TEST);
        cliente.setGenero(TestConstant.GENDER);
        cliente.setEdad(TestConstant.AGE);
        cliente.setIdentificacion(TestConstant.IDENTIFICATION);
        cliente.setDireccion(TestConstant.ADDRESS_TEST);
        cliente.setTelefono(TestConstant.PHONE_NUMBER_TEST);
        cliente.setClienteId(TestConstant.CUSTOMER_ID_TEST);
        cliente.setContrasena(TestConstant.PASSWORD_TEST);
        cliente.setEstado(TestConstant.STATUS_TEST);

        return cliente;
    }
}
