package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.domain.Detalle;
import com.example.demo.dto.DetalleDTO;

@Mapper
public interface DetalleMapper {
	@Mapping(source = "factura.numFactura", target = "numFactura_Factura")
	@Mapping(source = "producto.idProducto", target = "idProducto_Producto")
	@Mapping(source = "producto.nombre", target = "nombreProducto")
	public DetalleDTO detalleToDetalleDTO(Detalle detalle);

	@Mapping(source = "numFactura_Factura", target = "factura.numFactura")
	@Mapping(source = "idProducto_Producto", target = "producto.idProducto")
	@Mapping(source = "nombreProducto", target = "producto.nombre")
	public Detalle detalleDTOToDetalle(DetalleDTO detalleDTO);

	public List<DetalleDTO> listDetalleToListDetalleDTO(List<Detalle> detalles);

	public List<Detalle> listDetalleDTOToListDetalle(List<DetalleDTO> detalleDTOs);
}
