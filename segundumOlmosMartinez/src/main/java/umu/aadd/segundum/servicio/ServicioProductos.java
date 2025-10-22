package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.Map;

import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

/**
 * Implementación del servicio de productos.
 */
public class ServicioProductos implements IServicioProductos {

	@Override
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			String idCategoria, boolean envioDisponible, String idUsuarioVendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarDatosProducto(String idProducto, String descripcion, double precio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirVisualizacion(String idProducto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Producto, String> getHistorial(Month mes, int anio) {
		// TODO Auto-generated method stub
		return null;
	}

}
