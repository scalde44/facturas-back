package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
	@Query("SELECT de FROM Detalle de WHERE de.factura.numFactura=:nFactura")
	public List<Detalle> findShcaByNumFactura(Integer nFactura);
}
