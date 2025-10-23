package umu.aadd.segundum.servicio;

import java.time.Month;
import java.util.List;
import java.util.Map;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.LugarRecogida;
import umu.aadd.segundum.modelo.Producto;
import umu.aadd.segundum.modelo.Usuario;

/**
 * Implementación del servicio de productos.
 */
public class ServicioProductos implements IServicioProductos {

	private Repositorio<Producto, String> repoProductos = FactoriaRepositorios.getRepositorio(Producto.class);
	private ServicioCategorias servicioCategorias = new ServicioCategorias();
	private ServicioUsuarios servicioUsuarios = new ServicioUsuarios();

	@Override
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			String idCategoria, boolean envioDisponible, String idUsuarioVendedor)
			throws RepositorioException, EntidadNoEncontrada {

		Categoria categoria = servicioCategorias.getById(idCategoria);
		Usuario usuario = servicioUsuarios.getById(idUsuarioVendedor);

		Producto producto = new Producto(titulo, descripcion, precio, estado, categoria, envioDisponible, usuario);
		repoProductos.add(producto);

		return producto.getId();
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repoProductos.getById(idProducto);
		LugarRecogida recogida = new LugarRecogida(descripcion, longitud, latitud);
		producto.setRecogida(recogida);
		repoProductos.update(producto);

	}

	@Override
	public void modificarDatosProducto(String idProducto, String descripcion, double precio)
			throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repoProductos.getById(idProducto);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		repoProductos.update(producto);

	}

	@Override
	public void anadirVisualizacion(String idProducto) throws RepositorioException, EntidadNoEncontrada {

		Producto producto = repoProductos.getById(idProducto);
		producto.setVisualizaciones(producto.getVisualizaciones() + 1);
		repoProductos.update(producto);

	}

	@Override
	public Map<Producto, String> getHistorial(Month mes, int anio) throws RepositorioException, EntidadNoEncontrada {
		
		/*repositorio = repoProductos.getAll();
		 * SELECT p FROM repositorio WHERE p.getFechaPublicacion.getMonth.isBefore(mes) AND p.getFechaPublicacion.getAnio.isBefore(anio);
		 * */
		
		return null;
	}

	@Override
	public List<Producto> getProductosVenta(Categoria categoria, String descripcion, EstadoProducto estado,
			double precio) throws RepositorioException, EntidadNoEncontrada {
		return null;
	}

}
