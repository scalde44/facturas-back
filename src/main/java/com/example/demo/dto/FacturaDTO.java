package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class FacturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Date fecha;
	private Integer numFactura;
	private Integer idCliente_Cliente;

	public FacturaDTO() {
		super();
	}

	public FacturaDTO(@NotNull Date fecha, Integer numFactura, Integer idCliente_Cliente) {
		super();
		this.fecha = fecha;
		this.numFactura = numFactura;
		this.idCliente_Cliente = idCliente_Cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(Integer numFactura) {
		this.numFactura = numFactura;
	}

	public Integer getIdCliente_Cliente() {
		return idCliente_Cliente;
	}

	public void setIdCliente_Cliente(Integer idCliente_Cliente) {
		this.idCliente_Cliente = idCliente_Cliente;
	}

}
