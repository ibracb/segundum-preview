package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
import utils.StringUtilidades;

/**
 * Controlador para modificar un producto.
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
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
		this.descripcion = productoActual.getProductoDTO().getDescripcion();
		this.precio = String.valueOf(productoActual.getProductoDTO().getPrecio());
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
		if (!StringUtilidades.isPrecioValido(precio)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Precio inválido"));
			return;
		}
		try {
			servicioProductos.modificarDatosProducto(productoActual.getProductoDTO().getId(), descripcion, Double.parseDouble(precio));
		} catch (Exception e) {
			error = true;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "PRODUCTO NO PUDO SER MODIFICADO", e.getMessage()));
		}
	}
}
