package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.dto.UsuarioDTO;
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
	public ProductoDTO altaProducto(String titulo, String descripcion, String precio, EstadoProducto estado,
			String idCategoria, boolean envioDisponible, String idUsuarioVendedor)
			throws RepositorioException, EntidadNoEncontrada {
		
		Categoria categoria = repositorioCategorias.getById(idCategoria);
		Usuario usuario = repositorioUsuarios.getById(idUsuarioVendedor);
		
		if (!StringUtilidades.isDatoValido(titulo) || !StringUtilidades.isPrecioValido(precio) || estado == null
				|| categoria == null || usuario == null) {
			return null;
		}

		Producto producto = new Producto(titulo, descripcion, Double.parseDouble(precio), estado, categoria, envioDisponible, usuario);
		repositorioProductos.add(producto);

		return convertirEnDTO(producto);
	}
	
	@Override
	public void asignarLugarRecogida(String idProducto, Double longitud, Double latitud, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repositorioProductos.getById(idProducto);

		if (producto != null && longitud >= LugarRecogida.LONGITUD_MINIMA && longitud <= LugarRecogida.LONGITUD_MAXIMA
				&& latitud >= LugarRecogida.LATITUD_MINIMA && latitud <= LugarRecogida.LATITUD_MAXIMA
				&& StringUtilidades.isDatoValido(descripcion)) {
			producto.setRecogida(descripcion, longitud, latitud);
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
	public List<ProductoDTO> getHistorial(Month mes, int anio) throws RepositorioException, EntidadNoEncontrada {
		List<Producto> productos = repositorioProductos.recuperarHistorial(mes, anio);
		List<ProductoDTO> productosDTO = new LinkedList<>();
		productos.forEach(producto -> {
			productosDTO.add(convertirEnDTO(producto));
		});
		return productosDTO;
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
				producto.getVisualizaciones(), producto.isEnvioDisponible(), producto.getRecogida(),
				producto.getNombreCompletoVendedor());
	}
	
	@Override
	public List<ProductoDTO> mostrarProductosDTOVenta() throws RepositorioException, EntidadNoEncontrada{
		List<Producto> productos = getProductosVenta(null, null, null, -1);
		List<ProductoDTO> prodsDTO = new LinkedList<>();
		for(Producto p : productos) {
			prodsDTO.add(recuperarProductoDTO(p.getId()));
		}
		return prodsDTO;
	}
	
	@Override
	public List<ProductoDTO> recuperarProductosDTOPropios(UsuarioDTO usuario) throws RepositorioException, EntidadNoEncontrada{
		List<Producto> productos = repositorioProductos.recuperarProductosVentaPropios(usuario);
		List<ProductoDTO> prodsDTO = new LinkedList<>();
		for(Producto p : productos) {
			prodsDTO.add(recuperarProductoDTO(p.getId()));
		}
		return prodsDTO;
	}
	
	@Override
	public List<ProductoDTO> buscarProductosDTOVenta(String categoriaId, String descripcion, 
	        EstadoProducto estado, Double precioMaximo) throws RepositorioException, EntidadNoEncontrada {
	    List<Categoria> categorias = null;
	    if (categoriaId != null && !categoriaId.isEmpty()) {
	        categorias = repositorioCategorias.getDescendientes(categoriaId);
	    }
	    double precio = precioMaximo != null ? precioMaximo : Double.MAX_VALUE;
	    List<Producto> productos = repositorioProductos.recuperarProductosVenta(categorias, descripcion, estado, precio);
	    List<ProductoDTO> productosDTO = new LinkedList<>();
	    for (Producto p : productos) {
	        ProductoDTO dto = convertirEnDTO(p);
	        productosDTO.add(dto);
	    }
	    return productosDTO;
	}
 
}
