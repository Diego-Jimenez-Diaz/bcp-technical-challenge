package com.globant.djimenez.pruebatecnica.controller;

import com.globant.djimenez.pruebatecnica.dto.request.MovimientoDTORequest;
import com.globant.djimenez.pruebatecnica.dto.request.MovimientoUpdateDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.MovimientoDTOResponse;
import com.globant.djimenez.pruebatecnica.service.MovimientoService;
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

@RequestMapping(value = "/movimientos")
@RestController
public class MovimientoController {
    @Autowired
    MovimientoService movimientoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTOResponse> createMovimiento(@RequestBody MovimientoDTORequest movimientoDTORequest) {
        return new ResponseEntity<>(movimientoService.createMovimiento(movimientoDTORequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTOResponse> getMovimientoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movimientoService.getMovimientoById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTOResponse> updateMovimiento(@PathVariable("id") Long id, @RequestBody MovimientoUpdateDTORequest movimientoUpdateDTORequest) {
        return new ResponseEntity<>(movimientoService.updateMovimiento(id, movimientoUpdateDTORequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTOResponse> updatePartialMovimiento(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(movimientoService.updatePartialMovimiento(id, fields), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable("id") Long id) {
        movimientoService.deleteMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
