package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
import utils.StringUtilidades;

/**
 * Controlador para modificar un producto.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorModificarProducto implements Serializable {

	/**
	 * Servicio de productos.
	 */
	private IServicioProductos servicioProductos;
	
	@SuppressWarnings("unused")
	private IServicioCategorias servicioCategorias;

	@SuppressWarnings("unused")
	private boolean error;
	
	/**
	 * Descripción del producto.
	 */
	private String descripcion;
	
	/**
	 * Precio del producto.
	 */
	private String precio;
	
	/**
	 * Producto que se está modificando en esta vista.
	 */
	private ProductoDTO producto;
	
	/**
	 * Sesión del usuario.
	 */
	@SuppressWarnings("unused")
	@Inject
	private SesionUsuario sesionUsuario;
	
	/**
	 * Producto actual a modificar.
	 */
	@Inject
	private ProductoActual productoActual;
	
	/**
	 * Inicializa los datos del producto actual.
	 */
	@PostConstruct
    public void init() {
		this.producto = productoActual.getProductoDTO();
	    this.descripcion = producto.getDescripcion();
	    this.precio = String.valueOf(producto.getPrecio());
    }
	
	/**
	 * Constructor del controlador.
	 */
	public ControladorModificarProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	}
	
	/**
	 * Obtiene la descripción del producto.
	 * @return	 La descripción del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece la descripción del producto.
	 * @param descripcion	 La nueva descripción del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el precio del producto.
	 * @return	 El precio del producto.
	 */
	public String getPrecio() {
		return precio;
	}
	
	/**
	 * Establece el precio del producto.
	 * @param precio	 El nuevo precio del producto.
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	/**
	 * Modifica el producto con los nuevos datos.
	 */
	public void modificarProducto() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		double precioAEnviar;
		if (precio != null && !precio.trim().isEmpty()) {
		    if (!StringUtilidades.isPrecioValido(precio)) {
		        facesContext.addMessage(null,
		                new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Precio inválido"));
		        return;
		    }
		    precioAEnviar = Double.parseDouble(precio);
		} else {
		    precioAEnviar = producto.getPrecio();
		}

		try {
		    servicioProductos.modificarDatosProducto(
		        producto.getId(),
		        descripcion,
		        precioAEnviar
		    );
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "PRODUCTO MODIFICADO", "El producto ha sido modificado correctamente"));
		} catch (Exception e) {
			error = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "PRODUCTO NO PUDO SER MODIFICADO", e.getMessage()));
		}
	}
}
