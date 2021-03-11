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

import com.example.demo.domain.Detalle;
import com.example.demo.dto.DetalleDTO;
import com.example.demo.mapper.DetalleMapper;
import com.example.demo.service.DetalleService;

@RestController
@RequestMapping("/api/v1/detalle")
@CrossOrigin(origins = "*")
public class DetalleRestController {
	@Autowired
	private DetalleService detalleService;
	@Autowired
	private DetalleMapper detalleMapper;

	@GetMapping(value = "/{numDetalle}")
	public ResponseEntity<?> findById(@PathVariable("numDetalle") Integer numDetalle) throws Exception {

		Detalle detalle = (detalleService.findById(numDetalle).isPresent() == true)
				? detalleService.findById(numDetalle).get()
				: null;

		return ResponseEntity.ok().body(detalleMapper.detalleToDetalleDTO(detalle));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(detalleMapper.listDetalleToListDetalleDTO(detalleService.findAll()));
	}

	@GetMapping("/findByNumFactura/{nFactura}")
	public ResponseEntity<?> findByNumFactura(@PathVariable("nFactura") Integer nFactura) throws Exception {

		return ResponseEntity.ok()
				.body(detalleMapper.listDetalleToListDetalleDTO(detalleService.findShcaByNumFactura(nFactura)));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody DetalleDTO detalleDTO) throws Exception {

		Detalle detalle = detalleMapper.detalleDTOToDetalle(detalleDTO);
		detalle = detalleService.save(detalle);

		return ResponseEntity.ok().body(detalleMapper.detalleToDetalleDTO(detalle));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody DetalleDTO detalleDTO) throws Exception {

		Detalle detalle = detalleMapper.detalleDTOToDetalle(detalleDTO);
		detalle = detalleService.update(detalle);

		return ResponseEntity.ok().body(detalleMapper.detalleToDetalleDTO(detalle));
	}

	@DeleteMapping(value = "/{numDetalle}")
	public ResponseEntity<?> delete(@PathVariable("numDetalle") Integer numDetalle) throws Exception {

		detalleService.deleteById(numDetalle);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(detalleService.count());
	}
}
