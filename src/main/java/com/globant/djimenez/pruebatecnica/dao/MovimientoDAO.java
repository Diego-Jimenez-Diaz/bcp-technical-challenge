package com.globant.djimenez.pruebatecnica.dao;

import com.globant.djimenez.pruebatecnica.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoDAO extends JpaRepository<Movimiento, Long> {
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select * from movimientos m where m.cuenta = :id and m.fecha >= :from and m.fecha <= :to")
    List<Movimiento> getMovimientosByCuentaIdAndDateRange(@Param("id") Long id, @Param("from") LocalDate from,@Param("to") LocalDate to);
}
