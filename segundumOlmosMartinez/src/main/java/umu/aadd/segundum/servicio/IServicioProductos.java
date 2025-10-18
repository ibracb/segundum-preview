package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.Map;

import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

/**
 * Interfaz del servicio de gestión de productos.
 */
public interface IServicioProductos {
	
	/**
	 * Da de alta un nuevo producto en el sistema.
	 * 
	 * @param titulo             Título del producto.
	 * @param descripcion        Descripción del producto.
	 * @param precio             Precio del producto.
	 * @param estado             Estado del producto.
	 * @param idCategoria        Identificador de la categoría del producto.
	 * @param envioDisponible    Indica si el envío está disponible para el producto.
	 * @param idUsuarioVendedor  Identificador del usuario vendedor.
	 * @return Identificador único del producto creado.
	 */
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado, String idCategoria, boolean envioDisponible, String idUsuarioVendedor);
	
	/**
	 * Asigna un lugar de recogida para un producto.
	 * 
	 * @param idProducto  Identificador del producto.
	 * @param latitud     Latitud del lugar de recogida.
	 * @param longitud    Longitud del lugar de recogida.
	 * @param descripcion Descripción del lugar de recogida.
	 */
	public void asignarLugarRecogida(String idProducto, double latitud, double longitud, String descripcion);
	
	/**
	 * Modifica los datos de un producto existente.
	 * 
	 * @param idProducto  Identificador del producto a modificar.
	 * @param descripcion Nueva descripción del producto.
	 * @param precio      Nuevo precio del producto.
	 */
	public void modificarDatosProducto(String idProducto, String descripcion, double precio);
	
	/**
	 * Añade una visualización al contador del producto.
	 * 
	 * @param idProducto Identificador del producto.
	 */
	public void anadirVisualizacion(String idProducto);
	
	/**
	 * Recupera el historial de un producto para un mes y año específicos.
	 * 
	 * @param mes  Mes del historial.
	 * @param anio Año del historial.
	 * @return Mapa con los productos y sus respectivos historiales en forma de cadena.
	 */
	public Map<Producto, String> getHistorial(Month mes, int anio);
	
	
	
}
