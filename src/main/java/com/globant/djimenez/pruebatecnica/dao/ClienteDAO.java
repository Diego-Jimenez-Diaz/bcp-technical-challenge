package com.globant.djimenez.pruebatecnica.dao;

import com.globant.djimenez.pruebatecnica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}
