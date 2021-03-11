package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String apellido;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String direccion;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String email;
	@NotNull
	private Date fechaNacimiento;
	private Integer idCliente;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String nombre;
	@NotNull
	@NotEmpty
	@Size(max = 20)
	private String telefono;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(@NotNull @NotEmpty @Size(max = 255) String apellido,
			@NotNull @NotEmpty @Size(max = 255) String direccion, @NotNull @NotEmpty @Size(max = 255) String email,
			@NotNull Date fechaNacimiento, Integer idCliente, @NotNull @NotEmpty @Size(max = 255) String nombre,
			@NotNull @NotEmpty @Size(max = 20) String telefono) {
		super();
		this.apellido = apellido;
		this.direccion = direccion;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono = telefono;
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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

}
