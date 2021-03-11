package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.domain.Factura;
import com.example.demo.dto.FacturaDTO;

@Mapper
public interface FacturaMapper {
	@Mapping(source = "cliente.idCliente", target = "idCliente_Cliente")
	public FacturaDTO facturaToFacturaDTO(Factura factura);

	@Mapping(source = "idCliente_Cliente", target = "cliente.idCliente")
	public Factura facturaDTOToFactura(FacturaDTO facturaDTO);

	public List<FacturaDTO> listFacturaToListFacturaDTO(List<Factura> facturas);

	public List<Factura> listFacturaDTOToListFactura(List<FacturaDTO> facturaDTOs);
}
