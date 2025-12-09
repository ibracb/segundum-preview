package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import umu.aadd.segundum.dto.ProductoDTO;

@SuppressWarnings("serial")
@Named
@ViewScoped
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
