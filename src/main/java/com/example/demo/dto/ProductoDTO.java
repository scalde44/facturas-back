package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String nombre;
	@NotNull
	private Integer precio;
	@NotNull
	private Integer stock;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(Integer idProducto, @NotNull @NotEmpty @Size(max = 255) String nombre, @NotNull Integer precio,
			@NotNull Integer stock) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
