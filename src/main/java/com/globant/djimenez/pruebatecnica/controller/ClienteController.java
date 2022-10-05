package com.globant.djimenez.pruebatecnica.controller;

import com.globant.djimenez.pruebatecnica.dto.request.ClienteDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.ClienteDTOResponse;
import com.globant.djimenez.pruebatecnica.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(value = "/clientes")
@RestController
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTOResponse> createCliente(@RequestBody ClienteDTORequest clienteDTORequest) {
        return new ResponseEntity<>(clienteService.createCliente(clienteDTORequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTOResponse> getClienteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTOResponse> updateCliente(@PathVariable("id") Long id, @RequestBody ClienteDTORequest clienteDTORequest) {
        return new ResponseEntity<>(clienteService.updateCliente(id, clienteDTORequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTOResponse> partialUpdateCliente(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(clienteService.updatePartialCliente(id, fields), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
