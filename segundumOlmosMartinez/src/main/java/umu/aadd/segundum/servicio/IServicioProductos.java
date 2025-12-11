package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.LugarRecogida;
import umu.aadd.segundum.modelo.Producto;

/**
 * Interfaz del servicio de gestión de productos.
 */
public interface IServicioProductos {

	/**
	 * Da de alta un nuevo producto en el sistema.
	 * 
	 * @param titulo            Título del producto.
	 * @param descripcion       Descripción del producto.
	 * @param precio            Precio del producto.
	 * @param estado            Estado del producto.
	 * @param idCategoria       Identificador de la categoría del producto.
	 * @param envioDisponible   Indica si el envío está disponible para el producto.
	 * @param idUsuarioVendedor Identificador del usuario vendedor.
	 * @return Identificador único del producto creado.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra la categoría o el usuario
	 *                              vendedor.
	 */
	String altaProducto(String titulo, String descripcion, String precio, EstadoProducto estado, String idCategoria, boolean envioDisponible,
			String idUsuarioVendedor) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Asigna un lugar de recogida para un producto.
	 * 
	 * @param idProducto  Identificador del producto.
	 * @param longitud    Longitud del lugar de recogida.
	 * @param latitud     Latitud del lugar de recogida.
	 * @param descripcion Descripción del lugar de recogida.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra el producto.
	 */
	void asignarLugarRecogida(String idProducto, Double longitud, Double latitud, String descripcion)
			throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Modifica los datos de un producto existente.
	 * 
	 * @param idProducto  Identificador del producto a modificar.
	 * @param descripcion Nueva descripción del producto.
	 * @param precio      Nuevo precio del producto.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra el producto.
	 */
	void modificarDatosProducto(String idProducto, String descripcion, double precio) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Añade una visualización al contador del producto.
	 * 
	 * @param idProducto Identificador del producto.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra el producto.
	 */
	void anadirVisualizacion(String idProducto) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera el historial de productos para un mes y año específicos.
	 * 
	 * @param mes  Mes del historial.
	 * @param anio Año del historial.
	 * @return Mapa con los productos y sus respectivos historiales en forma de
	 *         cadena.
	 */
	List<ProductoDTO> getHistorial(Month mes, int anio) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera los productos a la venta que cumplan con las caracteristicas
	 * especificadas.
	 * 
	 * @param categoria   Categoria del producto.
	 * @param descripcion Descripcion del producto.
	 * @param estado      Estado del producto.
	 * @param precio      Precio del producto.
	 * @return Lista con los productos ordenados que cumplen con los requisitos
	 *         especificados.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra alguna entidad relacionada.
	 */
	List<Producto> getProductosVenta(String categoriaId, String descripcion, EstadoProducto estado, double precio)
			throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera un usuario por su identificador.
	 * 
	 * @param idProducto Identificador del producto a recuperar.
	 * @return Producto correspondiente al identificador especificado.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si el producto con el identificador especificado
	 *                              no existe.
	 */
	Producto recuperarProducto(String idProducto) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera un producto en formato DTO por su identificador.
	 * 
	 * @param id Identificador del producto a recuperar.
	 * @return ProductoDTO correspondiente al identificador especificado.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada  Si el producto con el identificador especificado
	 *                              no existe.
	 */
	ProductoDTO recuperarProductoDTO(String idProducto) throws RepositorioException, EntidadNoEncontrada;
	
	List<ProductoDTO> mostrarProductosDTOVenta() throws RepositorioException, EntidadNoEncontrada;
	
	List<ProductoDTO> recuperarProductosDTOPropios(UsuarioDTO usuario) throws RepositorioException, EntidadNoEncontrada;
}
