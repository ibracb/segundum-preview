package umu.aadd.segundum.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.repositorio.RepositorioCategoriasAdHoc;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorCrearProducto implements Serializable {

	private String titulo;

	private String descripcion;

	private double precio;

	private EstadoProducto estado;

	private String categoria;
	
	private boolean envioDisponible;
	
	private String vendedor;

	private IServicioProductos servicioProductos;
	
	private IServicioCategorias servicioCategorias;

	private boolean error;
	
	
	public ControladorCrearProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	}
	
	public void crearProducto() {
		
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
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
