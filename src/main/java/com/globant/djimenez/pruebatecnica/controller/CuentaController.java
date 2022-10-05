package com.globant.djimenez.pruebatecnica.controller;

import com.globant.djimenez.pruebatecnica.dto.request.CuentaDTORequest;
import com.globant.djimenez.pruebatecnica.dto.response.CuentaDTOResponse;
import com.globant.djimenez.pruebatecnica.service.CuentaService;
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

@RequestMapping(value = "/cuentas")
@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTOResponse> createCuenta(@RequestBody CuentaDTORequest cuentaDTORequest) {
        return new ResponseEntity<>(cuentaService.createCuenta(cuentaDTORequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTOResponse> getCuentaById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cuentaService.getCuentaById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTOResponse> updateCuenta(@PathVariable("id") Long id, @RequestBody CuentaDTORequest cuentaDTORequest) {
        return new ResponseEntity<>(cuentaService.updateCuenta(id, cuentaDTORequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTOResponse> updatePartialCuenta(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(cuentaService.updatePartialCuenta(id, fields), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable("id") Long id) {
        cuentaService.deleteCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
