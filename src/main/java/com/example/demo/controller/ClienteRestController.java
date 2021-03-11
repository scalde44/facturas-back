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

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "*")
public class ClienteRestController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteMapper clienteMapper;

	@GetMapping(value = "/{idCliente}")
	public ResponseEntity<?> findById(@PathVariable("idCliente") Integer idCliente) throws Exception {

		Cliente cliente = (clienteService.findById(idCliente).isPresent() == true)
				? clienteService.findById(idCliente).get()
				: null;

		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(clienteService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ClienteDTO clienteDTO) throws Exception {

		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		cliente = clienteService.save(cliente);

		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO clienteDTO) throws Exception {

		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		cliente = clienteService.update(cliente);

		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
	}

	@DeleteMapping(value = "/{idCliente}")
	public ResponseEntity<?> delete(@PathVariable("idCliente") Integer idCliente) throws Exception {

		clienteService.deleteById(idCliente);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(clienteService.count());
	}
}
