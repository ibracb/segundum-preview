package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
import utils.StringUtilidades;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorModificarProducto implements Serializable {

private IServicioProductos servicioProductos;
	
	private IServicioCategorias servicioCategorias;

	private boolean error;
	
	private String descripcion;
	
	private String precio;
	
	@Inject
	private SesionUsuario sesionUsuario;
	
	@Inject
	private ProductoActual productoActual;
	
	@PostConstruct
    public void init() {
		this.descripcion = productoActual.getProductoDTO().getDescripcion();
		this.precio = String.valueOf(productoActual.getProductoDTO().getPrecio());
    }
	
	public ControladorModificarProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
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
