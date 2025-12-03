package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.List;
import java.util.Map;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.dto.ProductoDTO;
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
	 * Repositorio de productos.
	 */
	private RepositorioProductosAdHoc repositorioProductos = FactoriaRepositorios.getRepositorio(Producto.class);

	/**
	 * Repositorio de categorias.
	 */
	private RepositorioCategoriasAdHoc repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);

	/**
	 * Repositorio de usuarios.
	 */
	private Repositorio<Usuario, String> repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			String idCategoria, boolean envioDisponible, String idUsuarioVendedor)
			throws RepositorioException, EntidadNoEncontrada {

		Categoria categoria = repositorioCategorias.getById(idCategoria);
		Usuario usuario = repositorioUsuarios.getById(idUsuarioVendedor);

		if (!StringUtilidades.isDatoValido(titulo) || precio < Producto.PRECIO_GRATUITO || estado == null
				|| categoria == null || usuario == null) {
			return null;
		}

		Producto producto = new Producto(titulo, descripcion, precio, estado, categoria, envioDisponible, usuario);
		repositorioProductos.add(producto);

		return producto.getId();
	}

	//desde el usuario en sí?
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
//recuperar historial tiene que devolver un DTO
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
		if (repositorioProductos.getIds().stream().anyMatch(id -> id.equals(idProducto))) {
			Producto producto = repositorioProductos.getById(idProducto);
			if (producto == null) {
				System.err.println("No se puede recuperar el producto con id " + idProducto
						+ " porque no se encuentra en el repositorio");
				return null;
			}
			return producto;
		}
		return null;
	}
	
	@Override
	public ProductoDTO recuperarProductoDTO(String idProducto) throws RepositorioException, EntidadNoEncontrada {
		if (idProducto == null || idProducto.isEmpty()) {
			throw new IllegalArgumentException("idProducto: no debe ser nulo ni vacio");
		}
		return convertirEnDTO(repositorioProductos.getById(idProducto));
	}
	
	/**
	 * Convierte un producto en su representación DTO.
	 * 
	 * @param producto Producto a convertir.
	 * @return ProductoDTO correspondiente al producto especificado.
	 */
	private ProductoDTO convertirEnDTO(Producto producto) {
		return new ProductoDTO(producto.getId(), producto.getTitulo(), producto.getDescripcion(), producto.getPrecio(),
				producto.getEstadoFormateado(), producto.getFechaPublicacion(), producto.getNombreCategoria(),
				producto.getVisualizaciones(), producto.isEnvioDisponible(), producto.getDescripcionLugarRecogida(),
				producto.getNombreCompletoVendedor());
	}

}
