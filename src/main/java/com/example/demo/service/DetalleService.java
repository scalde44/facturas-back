package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Detalle;

public interface DetalleService extends GenericService<Detalle, Integer> {
	public List<Detalle> findShcaByNumFactura(Integer nFactura) throws Exception;
}
