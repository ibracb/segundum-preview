package umu.aadd.segundum.repositorio;

import java.time.Month;
import java.util.List;

import repositorio.RepositorioException;
import repositorio.RepositorioString;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

/**
 * Repositorio ad-hoc para productos.
 */
public interface RepositorioProductosAdHoc extends RepositorioString<Producto> {

	/**
	 * Recupera el historial de productos publicados en un mes y año específicos.
	 * 
	 * @param mes  Mes de publicación.
	 * @param anio Año de publicación.
	 * @return Mapa con los productos y su información en formato String.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	List<Producto> recuperarHistorial(Month mes, int anio) throws RepositorioException;

	/**
	 * Recupera productos en venta que coinciden con los criterios especificados.
	 * 
	 * @param categoria   Categoría del producto.
	 * @param descripcion Descripción del producto.
	 * @param estado      Estado del producto.
	 * @param precio      Precio máximo del producto.
	 * @return Lista de productos que cumplen con los criterios.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	List<Producto> recuperarProductosVenta(List<Categoria> categorias, String descripcion, EstadoProducto estado, double precio)
			throws RepositorioException;

	List<Producto> recuperarProductosVentaPropios(UsuarioDTO usuario) throws RepositorioException;
}
