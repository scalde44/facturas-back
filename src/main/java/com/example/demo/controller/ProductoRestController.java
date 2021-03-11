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

import com.example.demo.domain.Producto;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.mapper.ProductoMapper;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/api/v1/producto")
@CrossOrigin(origins = "*")
public class ProductoRestController {
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ProductoMapper productoMapper;

	@GetMapping(value = "/{idProducto}")
	public ResponseEntity<?> findById(@PathVariable("idProducto") Integer idProducto) throws Exception {

		Producto producto = (productoService.findById(idProducto).isPresent() == true)
				? productoService.findById(idProducto).get()
				: null;

		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(productoMapper.listProductoToListProductoDTO(productoService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {

		Producto producto = productoMapper.productoDTOToProducto(productoDTO);
		producto = productoService.save(producto);

		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {

		Producto producto = productoMapper.productoDTOToProducto(productoDTO);
		producto = productoService.update(producto);

		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));
	}

	@DeleteMapping(value = "/{idProducto}")
	public ResponseEntity<?> delete(@PathVariable("idProducto") Integer idProducto) throws Exception {

		productoService.deleteById(idProducto);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(productoService.count());
	}
}
