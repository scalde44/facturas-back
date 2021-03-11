package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DetalleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer cantidad;
	private Integer numDetalle;
	@NotNull
	private Integer precio;
	private Integer numFactura_Factura;
	private Integer idProducto_Producto;
	private String nombreProducto;

	public DetalleDTO() {
		super();
	}

	public DetalleDTO(@NotNull Integer cantidad, Integer numDetalle, @NotNull Integer precio,
			Integer numFactura_Factura, Integer idProducto_Producto, String nombreProducto) {
		super();
		this.cantidad = cantidad;
		this.numDetalle = numDetalle;
		this.precio = precio;
		this.numFactura_Factura = numFactura_Factura;
		this.idProducto_Producto = idProducto_Producto;
		this.nombreProducto = nombreProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getNumDetalle() {
		return numDetalle;
	}

	public void setNumDetalle(Integer numDetalle) {
		this.numDetalle = numDetalle;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getNumFactura_Factura() {
		return numFactura_Factura;
	}

	public void setNumFactura_Factura(Integer numFactura_Factura) {
		this.numFactura_Factura = numFactura_Factura;
	}

	public Integer getIdProducto_Producto() {
		return idProducto_Producto;
	}

	public void setIdProducto_Producto(Integer idProducto_Producto) {
		this.idProducto_Producto = idProducto_Producto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

}
