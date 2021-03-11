package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.domain.Producto;
import com.example.demo.dto.ProductoDTO;

@Mapper
public interface ProductoMapper {
	public ProductoDTO productoToProductoDTO(Producto producto);

	public Producto productoDTOToProducto(ProductoDTO productoDTO);

	public List<ProductoDTO> listProductoToListProductoDTO(List<Producto> productos);

	public List<Producto> listProductoDTOToListProducto(List<ProductoDTO> productoDTOs);
}
