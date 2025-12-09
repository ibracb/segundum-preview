package umu.aadd.segundum.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.servicio.IServicioProductos;

/**
 * Bean de gestión de la página principal.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorPrincipal implements Serializable {
	
	/**
	 * Bean de sesión del usuario autenticado.
	 */
	@Inject
	private SesionUsuario sesionUsuario;
	
	@Inject
	private ProductoActual productoActual;
	
	private IServicioProductos servicioProductos;
	
	private List<ProductoDTO> productosVenta;
	
	/**
	 * Constructor del bean controlador principal.
	 */
	public ControladorPrincipal() {
		servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		productosVenta = new LinkedList<>();
	}
	
	/**
	 * Método para cerrar la sesión del usuario.
	 */
	public void logout() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().invalidateSession();
			sesionUsuario.cerrarSesion();
			facesContext.getExternalContext().redirect("inicio.xhtml");
		}
		catch (IOException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL CERRAR SESIÓN", e.getMessage()));
		}
	}
	
	public List<ProductoDTO> getProductosVenta() throws RepositorioException, EntidadNoEncontrada {
		productosVenta = servicioProductos.mostrarProductosDTOVenta();
        return productosVenta;
    }
	
	public void verProducto() throws RepositorioException, EntidadNoEncontrada {
		servicioProductos.anadirVisualizacion(productoActual.getProductoDTO().getId());
	}

}
