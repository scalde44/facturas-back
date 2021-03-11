package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
