package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "factura", schema = "public")
public class Factura implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_factura", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numFactura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	@NotNull
	private Cliente cliente;

	@NotNull
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factura")
	private List<Detalle> detalles = new ArrayList<>();

	public Factura() {
		super();
	}

	public Factura(Integer numFactura, @NotNull Cliente cliente, @NotNull Date fecha, List<Detalle> detalles) {
		super();
		this.numFactura = numFactura;
		this.cliente = cliente;
		this.fecha = fecha;
		this.detalles = detalles;
	}

	public Integer getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(Integer numFactura) {
		this.numFactura = numFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

}