package umu.aadd.segundum.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.servicio.IServicioProductos;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorMisProductos implements Serializable {

	/**
	 * Bean de sesión del usuario autenticado.
	 */
	@Inject
	private SesionUsuario sesionUsuario;
	
	@Inject 
	ProductoActual productoActual;
	
	private IServicioProductos servicioProductos;
	
	private List<ProductoDTO> productosVenta;
	
	public ControladorMisProductos() {
		servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		productosVenta = new LinkedList<>();
	}
	
	public List<ProductoDTO> getProductosVenta() throws RepositorioException, EntidadNoEncontrada {
		UsuarioDTO u = sesionUsuario.getUsuarioDTO();
		productosVenta = servicioProductos.recuperarProductosDTOPropios(u);
        return productosVenta;
    }
	
	public void modificarProducto() throws RepositorioException, EntidadNoEncontrada, IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().redirect("modificarProducto.xhtml");
    }
}
