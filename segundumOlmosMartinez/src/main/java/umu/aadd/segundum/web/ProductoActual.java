package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import umu.aadd.segundum.dto.ProductoDTO;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ProductoActual implements Serializable {
	private ProductoDTO productoDTO;
	
	public ProductoActual() {
	}
	
	public void setProductoDTO(ProductoDTO productoDTO) {
		this.productoDTO = productoDTO;
	}
	
	public ProductoDTO getProductoDTO() {
		return productoDTO;
	}
}
