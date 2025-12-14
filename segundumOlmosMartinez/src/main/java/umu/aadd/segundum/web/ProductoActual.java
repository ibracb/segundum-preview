package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import umu.aadd.segundum.dto.ProductoDTO;

/**
 * Clase que representa el producto actual en la sesión del usuario.
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ProductoActual implements Serializable {
	
	/**
	 * Producto actual dto.
	 */
	private ProductoDTO productoDTO;
	
	/**
	 * Constructor por defecto de ProductoActual.
	 */
	public ProductoActual() {
	}
	
	/**
	 * Establece el producto actual.
	 * 
	 * @param productoDTO El producto DTO a establecer como actual.
	 */
	public void setProductoDTO(ProductoDTO productoDTO) {
		this.productoDTO = productoDTO;
	}
	
	/**
	 * Obtiene el producto actual.
	 * 
	 * @return El producto DTO actual.
	 */
	public ProductoDTO getProductoDTO() {
		return productoDTO;
	}
}
