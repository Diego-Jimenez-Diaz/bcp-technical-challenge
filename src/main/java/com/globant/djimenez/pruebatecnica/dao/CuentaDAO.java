package com.globant.djimenez.pruebatecnica.dao;

import com.globant.djimenez.pruebatecnica.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CuentaDAO extends JpaRepository<Cuenta, Long> {
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select * from cuenta where cliente = :id")
    List<Cuenta> getAccountsBycustomerId(@Param("id") Long id);
}
