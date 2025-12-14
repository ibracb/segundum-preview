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
import umu.aadd.segundum.modelo.EstadoProducto;
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
	
	/**
	 * Bean de producto actual.
	 */
	@Inject
	private ProductoActual productoActual;
	
	/**
	 * Servicio de gestión de productos.
	 */
	private IServicioProductos servicioProductos;
	
	/**
	 * Lista de productos en venta.
	 */
	private List<ProductoDTO> productosVenta;
	
	/**
	 * Lista de todos los productos (sin filtrar).
	 */
	private List<ProductoDTO> todosLosProductos;
	
	/**
	 * Filtro de descripción.
	 */
	private String filtroDescripcion;
	
	/**
	 * Filtro de precio máximo.
	 */
	private Double filtroPrecioMaximo;
	
	/**
	 * Filtro de estado.
	 */
	private String filtroEstado;
	
	/**
	 * Filtro de categoría.
	 */
	private String filtroCategoria;
	
	/**
	 * Constructor del bean controlador principal.
	 */
	public ControladorPrincipal() {
		servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		productosVenta = new LinkedList<>();
		todosLosProductos = new LinkedList<>();
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
	
	/**
	 * Método para obtener la lista de productos en venta.
	 * 
	 * @return Lista de productos en venta.
	 * @throws RepositorioException    Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada     Si no se encuentran productos en venta.
	 */
	public List<ProductoDTO> getProductosVenta() throws RepositorioException, EntidadNoEncontrada {
		if (todosLosProductos.isEmpty()) {
			todosLosProductos = servicioProductos.mostrarProductosDTOVenta();
			productosVenta = new LinkedList<>(todosLosProductos);
		}
		return productosVenta;
	}
	
	/**
	 * Método para ver los detalles de un producto.
	 * 
	 * @throws RepositorioException    Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada     Si el producto no se encuentra.
	 * @throws IOException             Si ocurre un error de E/S al redirigir.
	 */
	public void verProducto() throws RepositorioException, EntidadNoEncontrada, IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().redirect("detallesProducto.xhtml");
		servicioProductos.anadirVisualizacion(productoActual.getProductoDTO().getId());
	}
	
	/**
	 * Método para buscar productos aplicando los filtros seleccionados.
	 */
	public void buscarPorFiltros() {
		try {
			EstadoProducto estadoEnum = null;
			if (filtroEstado != null && !filtroEstado.trim().isEmpty()) {
				estadoEnum = EstadoProducto.valueOf(filtroEstado);
			}
			productosVenta = servicioProductos.buscarProductosDTOVenta(
				filtroCategoria,
				filtroDescripcion,
				estadoEnum,
				filtroPrecioMaximo
			);
			if (todosLosProductos.isEmpty()) {
				todosLosProductos = servicioProductos.mostrarProductosDTOVenta();
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda completada", 
						"Se encontraron " + productosVenta.size() + " productos"));
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN LA BÚSQUEDA", e.getMessage()));
		}
	}
	
	/**
	 * Método para limpiar los filtros y mostrar todos los productos.
	 */
	public void limpiarFiltros() {
		filtroDescripcion = null;
		filtroPrecioMaximo = null;
		filtroEstado = null;
		filtroCategoria = null;
		
		productosVenta = new LinkedList<>(todosLosProductos);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Filtros limpiados", 
					"Mostrando todos los productos"));
	}

	/**
	 * Recupera el filtro de descripción.
	 * @return filtroDescripcion Filtro de descripción.
	 */
	public String getFiltroDescripcion() {
		return filtroDescripcion;
	}

	/**
	 * Establece el filtro de descripción.
	 * @param filtroDescripcion Filtro de descripción.
	 */
	public void setFiltroDescripcion(String filtroDescripcion) {
		this.filtroDescripcion = filtroDescripcion;
	}

	/**
	 * Recupera el filtro de precio máximo.
	 * @return filtroPrecioMaximo Filtro de precio máximo.
	 */
	public Double getFiltroPrecioMaximo() {
		return filtroPrecioMaximo;
	}

	/**
	 * Establece el filtro de precio máximo.
	 * @param filtroPrecioMaximo Filtro de precio máximo.
	 */
	public void setFiltroPrecioMaximo(Double filtroPrecioMaximo) {
		this.filtroPrecioMaximo = filtroPrecioMaximo;
	}

	/**
	 * Recupera el filtro de estado.
	 * @return filtroEstado Filtro de estado.
	 */
	public String getFiltroEstado() {
		return filtroEstado;
	}

	/**
	 * Establece el filtro de estado.
	 * @param filtroEstado Filtro de estado.
	 */
	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	/**
	 * Recupera el filtro de categoría.
	 * @return filtroCategoria Filtro de categoría.
	 */
	public String getFiltroCategoria() {
		return filtroCategoria;
	}

	/**
	 * Establece el filtro de categoría.
	 * @param filtroCategoria Filtro de categoría.
	 */
	public void setFiltroCategoria(String filtroCategoria) {
		this.filtroCategoria = filtroCategoria;
	}
	
	
}
