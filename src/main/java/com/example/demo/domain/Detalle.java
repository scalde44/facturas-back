package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle", schema = "public")
public class Detalle implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "num_detalle", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numDetalle;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_factura")
	@NotNull
	private Factura factura;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	@NotNull
	private Producto producto;
	@NotNull
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	@NotNull
	@Column(name = "precio", nullable = false)
	private Integer precio;

	public Detalle() {
		super();
	}

	public Detalle(Integer numDetalle, @NotNull Factura factura, @NotNull Producto producto, @NotNull Integer cantidad,
			@NotNull Integer precio) {
		super();
		this.numDetalle = numDetalle;
		this.factura = factura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Integer getNumDetalle() {
		return numDetalle;
	}

	public void setNumDetalle(Integer numDetalle) {
		this.numDetalle = numDetalle;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
