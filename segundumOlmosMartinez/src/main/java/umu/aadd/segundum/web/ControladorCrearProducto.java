package umu.aadd.segundum.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.LugarRecogida;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
import utils.StringUtilidades;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorCrearProducto implements Serializable {

	private String titulo;

	private String descripcion;

	private String precio;

	private EstadoProducto estado;

	private String categoria;

	private boolean envioDisponible;

	private String descripcionLugarRecogida;
	
	private Double longitudLugarRecogida;
	
	private Double latitudLugarRecogida;

	private String vendedor;

	private IServicioProductos servicioProductos;

	private IServicioCategorias servicioCategorias;

	private boolean error;

	@Inject
	private SesionUsuario sesionUsuario;

	public ControladorCrearProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	}

	public void crearProducto() {
		if (!StringUtilidades.isDatoValido(titulo) || !StringUtilidades.isDatoValido(descripcion)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Debe rellenar todos los datos"));
			return;
		}
		if (!StringUtilidades.isPrecioValido(precio)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Precio inválido"));
			return;
		}
		try {
			UsuarioDTO usuarioDTO = sesionUsuario.getUsuarioDTO();
			String idProducto = servicioProductos.altaProducto(titulo, descripcion, precio, estado, categoria,
					envioDisponible, usuarioDTO.getId());
			if(descripcionLugarRecogida != null && longitudLugarRecogida != 0.0 && latitudLugarRecogida != 0.0) {
				servicioProductos.asignarLugarRecogida(idProducto, longitudLugarRecogida, latitudLugarRecogida, descripcionLugarRecogida);
			}
			
			ProductoDTO productoDTO = servicioProductos.recuperarProductoDTO(idProducto);
			error = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
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
		} catch (Exception e) {
			error = true;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "PRODUCTO NO PUDO SER CREADO", e.getMessage()));
		}
	}

	public EstadoProducto[] getEstados() {
		return EstadoProducto.values();
	}

	public List<Categoria> getCategorias() throws RepositorioException {
		return servicioCategorias.recuperarTodasCategorias();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isEnvioDisponible() {
		return envioDisponible;
	}

	public void setEnvioDisponible(boolean envioDisponible) {
		this.envioDisponible = envioDisponible;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getDescripcionLugarRecogida() {
		return descripcionLugarRecogida;
	}

	public void setDescripcionLugarRecogida(String descripcion) {
		this.descripcionLugarRecogida = descripcion;
	}

	public Double getLongitudLugarRecogida() {
		return longitudLugarRecogida;
	}

	public void setLongitudLugarRecogida(Double longitud) {
		this.longitudLugarRecogida = longitud;
	}
	
	public Double getLatitudLugarRecogida() {
		return latitudLugarRecogida;
	}

	public void setLatitudLugarRecogida(Double latitud) {
		this.latitudLugarRecogida = latitud;
	}

	public IServicioProductos getServicioProductos() {
		return servicioProductos;
	}

	public void setServicioProductos(IServicioProductos servicioProductos) {
		this.servicioProductos = servicioProductos;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
