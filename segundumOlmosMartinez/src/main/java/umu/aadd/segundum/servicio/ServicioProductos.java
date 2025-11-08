package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.List;
import java.util.Map;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.LugarRecogida;
import umu.aadd.segundum.modelo.Producto;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.repositorio.RepositorioCategoriasAdHoc;
import umu.aadd.segundum.repositorio.RepositorioProductosAdHoc;
import utils.StringUtilidades;

/**
 * Implementación del servicio de productos.
 */
public class ServicioProductos implements IServicioProductos {

	/**
	 * Repositorio AdHoc de productos.
	 */
	private RepositorioProductosAdHoc repositorioProductos = FactoriaRepositorios.getRepositorio(Producto.class);
	
	/**
	 * Repositorio AdHoc de categorias.
	 */
	private RepositorioCategoriasAdHoc repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);

	/**
	 * Servicio de usuarios.
	 */
	private IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);

	@Override
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			String idCategoria, boolean envioDisponible, String idUsuarioVendedor)
			throws RepositorioException, EntidadNoEncontrada {

		Categoria categoria = repositorioCategorias.getById(idCategoria);
		Usuario usuario = servicioUsuarios.recuperarUsuario(idUsuarioVendedor);

		if (!StringUtilidades.isDatoValido(titulo) || precio < Producto.PRECIO_GRATUITO || estado == null
				|| categoria == null || usuario == null) {
			return null;
		}

		Producto producto = new Producto(titulo, descripcion, precio, estado, categoria, envioDisponible, usuario);
		repositorioProductos.add(producto);

		return producto.getId();
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repositorioProductos.getById(idProducto);

		if (producto != null && longitud >= LugarRecogida.LONGITUD_MINIMA && longitud <= LugarRecogida.LONGITUD_MAXIMA
				&& latitud >= LugarRecogida.LATITUD_MINIMA && latitud <= LugarRecogida.LATITUD_MAXIMA
				&& StringUtilidades.isDatoValido(descripcion)) {
			LugarRecogida recogida = new LugarRecogida(descripcion, longitud, latitud);
			producto.setRecogida(recogida);
			repositorioProductos.update(producto);
		}

	}

	@Override
	public void modificarDatosProducto(String idProducto, String descripcion, double precio)
			throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repositorioProductos.getById(idProducto);

		if (producto != null) {
			producto.setDescripcion(descripcion);

			if (precio >= Producto.PRECIO_GRATUITO) {
				producto.setPrecio(precio);
			}

			repositorioProductos.update(producto);
		}

	}

	@Override
	public void anadirVisualizacion(String idProducto) throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repositorioProductos.getById(idProducto);

		if (producto != null) {
			producto.setVisualizaciones(producto.getVisualizaciones() + 1);
			repositorioProductos.update(producto);
		}

	}

	@Override
	public Map<Producto, String> getHistorial(Month mes, int anio) throws RepositorioException, EntidadNoEncontrada {

		return repositorioProductos.recuperarHistorial(mes, anio);
	}

	@Override
	public List<Producto> getProductosVenta(String categoriaId, String descripcion, EstadoProducto estado,
			double precio) throws RepositorioException, EntidadNoEncontrada {
		List<Categoria> categorias = repositorioCategorias.getDescendientes(categoriaId);
		return repositorioProductos.recuperarProductosVenta(categorias, descripcion, estado, precio);
	}

	@Override
	public Producto recuperarProducto(String idProducto) throws RepositorioException, EntidadNoEncontrada {
		return repositorioProductos.getById(idProducto);
	}

}
