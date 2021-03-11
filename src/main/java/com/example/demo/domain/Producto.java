package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "producto", schema = "public")
public class Producto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_producto", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@NotNull
	@Column(name = "precio", nullable = false)
	private Integer precio;
	@NotNull
	@Column(name = "stock", nullable = false)
	private Integer stock;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	private List<Detalle> detalles = new ArrayList<>();

	public Producto() {
		super();
	}

	public Producto(Integer idProducto, @NotNull @NotEmpty @Size(max = 255) String nombre, @NotNull Integer precio,
			@NotNull Integer stock, List<Detalle> detalles) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.detalles = detalles;
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

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

}