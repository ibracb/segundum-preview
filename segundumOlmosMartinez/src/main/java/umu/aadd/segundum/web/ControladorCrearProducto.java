package umu.aadd.segundum.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;
import umu.aadd.segundum.repositorio.RepositorioProductosJPA;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
import utils.StringUtilidades;

/**
 * Controlador para la creación de productos.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorCrearProducto implements Serializable {

	/**
	 * Título del producto.
	 */
	private String titulo;

	/**
	 * Descripción del producto.
	 */
	private String descripcion;

	/**
	 * Precio del producto.
	 */
	private String precio;

	/**
	 * Estado del producto.
	 */
	private EstadoProducto estado;

	/**
	 * Categoría del producto.
	 */
	private String categoria;

	/**
	 * Indica si el envío está disponible.
	 */
	private boolean envioDisponible;

	/**
	 * Descripción del lugar de recogida.
	 */
	private String descripcionLugarRecogida;

	/**
	 * Longitud del lugar de recogida.
	 */
	private Double longitudLugarRecogida;

	/**
	 * Latitud del lugar de recogida.
	 */
	private Double latitudLugarRecogida;

	/**
	 * Vendedor del producto.
	 */
	private String vendedor;

	/**
	 * Servicio para la gestión de productos.
	 */
	private IServicioProductos servicioProductos;

	/**
	 * Servicio para la gestión de categorías.
	 */
	private IServicioCategorias servicioCategorias;

	/**
	 * Repositorio para la gestión de productos.
	 */
	private RepositorioProductosJPA repositorioProductos;

	/**
	 * Indica si hubo un error durante la creación del producto.
	 */
	private boolean error;

	/**
	 * Sesión del usuario actual.
	 */
	@Inject
	private SesionUsuario sesionUsuario;

	/**
	 * Constructor del controlador de creación de productos.
	 */
	public ControladorCrearProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
		this.repositorioProductos = FactoriaRepositorios.getRepositorio(Producto.class);
	}

	/**
	 * Crea un nuevo producto.
	 */
	public void crearProducto() {
		this.error = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!StringUtilidades.isDatoValido(titulo) || !StringUtilidades.isDatoValido(descripcion)) {
			this.error = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Debe rellenar todos los datos"));
			return;
		}
		if (!StringUtilidades.isPrecioValido(precio)) {
			this.error = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Precio inválido"));
			return;
		}
		
		boolean tieneLugarRecogidaValido = (descripcionLugarRecogida != null && !descripcionLugarRecogida.trim().isEmpty())
				&& (longitudLugarRecogida != null && longitudLugarRecogida != 0.0)
				&& (latitudLugarRecogida != null && latitudLugarRecogida != 0.0);

		if (!envioDisponible && !tieneLugarRecogidaValido) {
			this.error = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"PRODUCTO NO PUDO SER CREADO",
					"Si el envío no está disponible, entonces debe indicar la descripción del lugar de recogida, la longitud a la que se encuentra, que debe estar entre -180 y 180; y la latitud, entre -90 y 90"));
			return;
		}
		
		try {
			UsuarioDTO usuarioDTO = sesionUsuario.getUsuarioDTO();
			ProductoDTO productoDTO = servicioProductos.altaProducto(titulo, descripcion, precio, estado, categoria,
					envioDisponible, usuarioDTO.getId());
			if (descripcionLugarRecogida != null && longitudLugarRecogida != 0.0 && latitudLugarRecogida != 0.0) {
				servicioProductos.asignarLugarRecogida(productoDTO.getId(), longitudLugarRecogida, latitudLugarRecogida,
						descripcionLugarRecogida);
				productoDTO = servicioProductos.recuperarProductoDTO(productoDTO.getId());
			}
			if (!envioDisponible && productoDTO.getLugarRecogida() == null) {
				error = true;
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"PRODUCTO NO PUDO SER CREADO",
						"Si el envío no está disponible, entonces debe indicar la descripción del lugar de recogida, la longitud a la que se encuentra, que debe estar entre -180 y 180; y la latitud, entre -90 y 90"));
				Producto p = repositorioProductos.getById(productoDTO.getId());
				repositorioProductos.delete(p);
				return;
			}

			error = false;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Validación",
							"Producto satisfactoriamente creado!!" + "\nTítulo: " + productoDTO.getTitulo()
									+ "\nDescripción: " + productoDTO.getDescripcion() + "\nPrecio: "
									+ productoDTO.getPrecio() + "\nEstado: " + productoDTO.getEstado()
									+ "\nFecha de publicación: " + productoDTO.getFechaPublicacion().toString()
									+ "\nCategoría: " + productoDTO.getCategoria() + "\nEnvío disponible: "
									+ productoDTO.isEnvioDisponible() + "\nVendedor: " + productoDTO.getVendedor()));
			this.titulo = null;
			this.descripcion = null;
			this.precio = null;
			this.estado = null;
			this.categoria = null;
			this.envioDisponible = false;
			this.descripcionLugarRecogida = "";
			this.longitudLugarRecogida = 0.0;
			this.latitudLugarRecogida = 0.0;
		} catch (Exception e) {
			error = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "PRODUCTO NO PUDO SER CREADO", e.getMessage()));
		}
	}

	/**
	 * Recupera los estados de producto disponibles.
	 * 
	 * @return Array de estados de producto.
	 */
	public EstadoProducto[] getEstados() {
		return EstadoProducto.values();
	}

	/**
	 * Recupera todas las categorías disponibles ordenadas jerárquicamente
	 * con indentación para mostrar en el desplegable.
	 * 
	 * @return Lista de opciones de categoría con nombre indentado.
	 * @throws RepositorioException Si ocurre un error al recuperar las categorías.
	 */
	public List<CategoriaOption> getCategorias() throws RepositorioException {
		List<Categoria> categorias = servicioCategorias.recuperarCategoriasOrdenadasJerarquicamente();
		List<CategoriaOption> opciones = new ArrayList<>();
		for (Categoria c : categorias) {
			String indentacion = "－ ".repeat(c.getNivel());
			opciones.add(new CategoriaOption(c.getId(), indentacion + c.getNombre()));
		}
		return opciones;
	}

	/**
	 * Obtiene el título del producto.
	 * @return Título del producto.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título del producto.
	 * @param titulo Título del producto.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción del producto.
	 * @return Descripción del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del producto.
	 * @param descripcion Descripción del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el precio del producto.
	 * @return Precio del producto.
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto.
	 * @param precio Precio del producto.
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Obtiene el estado del producto.
	 * @return Estado del producto.
	 */
	public EstadoProducto getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del producto.
	 * @param estado Estado del producto.
	 */
	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene la categoría del producto.
	 * 
	 * @return Categoría del producto.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Establece la categoría del producto.
	 * 
	 * @param categoria Categoría del producto.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Indica si el envío está disponible.
	 * 
	 * @return true si el envío está disponible, false en caso contrario.
	 */
	public boolean isEnvioDisponible() {
		return envioDisponible;
	}

	/**
	 * Establece si el envío está disponible.
	 * 
	 * @param envioDisponible true si el envío está disponible, false en caso contrario.
	 */
	public void setEnvioDisponible(boolean envioDisponible) {
		this.envioDisponible = envioDisponible;
	}

	/**
	 * Obtiene el vendedor del producto.
	 * 
	 * @return Vendedor del producto.
	 */
	public String getVendedor() {
		return vendedor;
	}

	/**
	 * Establece el vendedor del producto.
	 * 
	 * @param vendedor Vendedor del producto.
	 */
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * Obtiene la descripción del lugar de recogida.
	 * 
	 * @return Descripción del lugar de recogida.
	 */
	public String getDescripcionLugarRecogida() {
		return descripcionLugarRecogida;
	}

	/**
	 * Establece la descripción del lugar de recogida.
	 * 
	 * @param descripcion Descripción del lugar de recogida.
	 */
	public void setDescripcionLugarRecogida(String descripcion) {
		this.descripcionLugarRecogida = descripcion;
	}

	/**
	 * Obtiene la longitud del lugar de recogida.
	 * 
	 * @return Longitud del lugar de recogida.
	 */
	public Double getLongitudLugarRecogida() {
		return longitudLugarRecogida;
	}

	/**
	 * Establece la longitud del lugar de recogida.
	 * 
	 * @param longitud Longitud del lugar de recogida.
	 */
	public void setLongitudLugarRecogida(Double longitud) {
		this.longitudLugarRecogida = longitud;
	}

	/**
	 * Obtiene la latitud del lugar de recogida.
	 * 
	 * @return Latitud del lugar de recogida.
	 */
	public Double getLatitudLugarRecogida() {
		return latitudLugarRecogida;
	}

	/**
	 * Establece la latitud del lugar de recogida.
	 * 
	 * @param latitud Latitud del lugar de recogida.
	 */
	public void setLatitudLugarRecogida(Double latitud) {
		this.latitudLugarRecogida = latitud;
	}

	/**
	 * Obtiene el servicio de productos.
	 * 
	 * @return Servicio de productos.
	 */
	public IServicioProductos getServicioProductos() {
		return servicioProductos;
	}

	/**
	 * Establece el servicio de productos.
	 * 
	 * @param servicioProductos Servicio de productos.
	 */
	public void setServicioProductos(IServicioProductos servicioProductos) {
		this.servicioProductos = servicioProductos;
	}

	/**
	 * Indica si hubo un error durante la creación del producto.
	 * 
	 * @return true si hubo un error, false en caso contrario.
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Establece el estado de error durante la creación del producto.
	 * 
	 * @param error true si hubo un error, false en caso contrario.
	 */
	public void setError(boolean error) {
		this.error = error;
	}
}
