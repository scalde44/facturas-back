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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente", schema = "public")
public class Cliente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cliente", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "apellido", nullable = false)
	private String apellido;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "direccion", nullable = false)
	private String direccion;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "email", nullable = false)
	private String email;
	@NotNull
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@NotNull
	@NotEmpty
	@Size(max = 20)
	@Column(name = "telefono", nullable = false)
	private String telefono;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Factura> facturas = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente, @NotNull @NotEmpty @Size(max = 255) String apellido,
			@NotNull @NotEmpty @Size(max = 255) String direccion, @NotNull @NotEmpty @Size(max = 255) String email,
			@NotNull Date fechaNacimiento, @NotNull @NotEmpty @Size(max = 255) String nombre,
			@NotNull @NotEmpty @Size(max = 20) String telefono, List<Factura> facturas) {
		super();
		this.idCliente = idCliente;
		this.apellido = apellido;
		this.direccion = direccion;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.facturas = facturas;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

}