package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Factura;
import com.example.demo.dto.FacturaDTO;
import com.example.demo.mapper.FacturaMapper;
import com.example.demo.service.FacturaService;

@RestController
@RequestMapping("/api/v1/factura")
@CrossOrigin(origins = "*")
public class FacturaRestController {
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private FacturaMapper facturaMapper;

	@GetMapping(value = "/{numFactura}")
	public ResponseEntity<?> findById(@PathVariable("numFactura") Integer numFactura) throws Exception {

		Factura factura = (facturaService.findById(numFactura).isPresent() == true)
				? facturaService.findById(numFactura).get()
				: null;

		return ResponseEntity.ok().body(facturaMapper.facturaToFacturaDTO(factura));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(facturaMapper.listFacturaToListFacturaDTO(facturaService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody FacturaDTO facturaDTO) throws Exception {

		Factura factura = facturaMapper.facturaDTOToFactura(facturaDTO);
		factura = facturaService.save(factura);

		return ResponseEntity.ok().body(facturaMapper.facturaToFacturaDTO(factura));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody FacturaDTO facturaDTO) throws Exception {

		Factura factura = facturaMapper.facturaDTOToFactura(facturaDTO);
		factura = facturaService.update(factura);

		return ResponseEntity.ok().body(facturaMapper.facturaToFacturaDTO(factura));
	}

	@DeleteMapping(value = "/{numFactura}")
	public ResponseEntity<?> delete(@PathVariable("numFactura") Integer numFactura) throws Exception {

		facturaService.deleteById(numFactura);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(facturaService.count());
	}
}
