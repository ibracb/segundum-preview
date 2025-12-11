package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorModificarProducto implements Serializable {

private IServicioProductos servicioProductos;
	
	private IServicioCategorias servicioCategorias;

	private boolean error;
	
	@Inject
	private SesionUsuario sesionUsuario;
	
	
	public ControladorModificarProducto() {
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	}
	
}
