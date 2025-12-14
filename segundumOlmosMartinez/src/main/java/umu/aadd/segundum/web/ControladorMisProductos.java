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

/**
 * Controlador para la gestión de los productos del usuario autenticado.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorMisProductos implements Serializable {

	/**
	 * Bean de sesión del usuario autenticado.
	 */
	@Inject
	private SesionUsuario sesionUsuario;
	
	/**
	 * Bean que mantiene el producto actual seleccionado.
	 */
	@Inject 
	ProductoActual productoActual;
	
	/**
	 * Servicio para la gestión de productos.
	 */
	private IServicioProductos servicioProductos;
	
	/**
	 * Lista de productos en venta del usuario.
	 */
	private List<ProductoDTO> productosVenta;
	
	/**
	 * Constructor que inicializa el servicio de productos y la lista de productos en venta.
	 */
	public ControladorMisProductos() {
		servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		productosVenta = new LinkedList<>();
	}
	
	/**
	 * Recupera la lista de productos en venta del usuario autenticado.
	 * 
	 * @return Lista de productos en venta.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada Si el usuario no es encontrado.
	 */
	public List<ProductoDTO> getProductosVenta() throws RepositorioException, EntidadNoEncontrada {
		UsuarioDTO u = sesionUsuario.getUsuarioDTO();
		productosVenta = servicioProductos.recuperarProductosDTOPropios(u);
        return productosVenta;
    }
	
	/**
	 * Establece el producto actual y redirige a la página de modificación de producto.
	 * @throws RepositorioException	 Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada	 Si el producto no es encontrado.
	 * @throws IOException			 Si ocurre un error de E/S durante la redirección.
	 */
	public void modificarProducto() throws RepositorioException, EntidadNoEncontrada, IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().redirect("modificarProducto.xhtml");
    }
}
