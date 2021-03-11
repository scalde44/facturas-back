package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {
	public ClienteDTO clienteToClienteDTO(Cliente cliente);

	public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);

	public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes);

	public List<Cliente> listClienteDTOToListCliente(List<ClienteDTO> clienteDTOs);
}
