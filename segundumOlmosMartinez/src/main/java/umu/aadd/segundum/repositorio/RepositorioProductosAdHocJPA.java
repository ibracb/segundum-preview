package umu.aadd.segundum.repositorio;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

/**
 * Implementación JPA del repositorio ad-hoc para productos.
 */
public class RepositorioProductosAdHocJPA extends RepositorioProductosJPA implements RepositorioProductosAdHoc {

	/**
	 * EntityManager para interactuar con la base de datos.
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	public Map<Producto, String> recuperarHistorial(Month mes, int anio) throws RepositorioException {
		Map<Producto, String> historial = new LinkedHashMap<>();
		try {
			List<Producto> productos = em.createQuery(
					"SELECT p FROM Producto p WHERE FUNCTION('MONTH', p.fechaPublicacion) = :mes) AND FUNCTION('YEAR', p.fechaPublicacion) = :anio)",
					Producto.class).setParameter("mes", mes.getValue()).setParameter("anio", anio).getResultList();
			for (Producto p : productos) {
				String texto = p.getId() + "," + p.getPrecio() + "," + p.getFechaPublicacion() + ","
						+ p.getCategoria().getNombre() + "," + p.getVisualizaciones();
				historial.put(p, texto);
			}
		} catch (Exception e) {
			throw new RepositorioException("Error al recuperar el historial", e);
		}
		return historial;
	}

	@Override
	public List<Producto> recuperarProductosVenta(Categoria categoria, String descripcion, EstadoProducto estado,
			double precio) throws RepositorioException {
		Map<String, Object> params = new HashMap<>();
		List<Categoria> categorias = new ArrayList<>();

		String textoQuery = "SELECT p FROM Producto p WHERE ";

		if (categoria != null) {
			textoQuery += "p.categoria IN :categorias ";
			categorias.add(categoria);
			categorias.addAll(categoria.getSubcategorias());
			params.put("categoria", categorias);
		}
		if (descripcion != null && !descripcion.isEmpty()) {
			textoQuery += "p.descripcion LIKE :descripcion AND ";
			params.put("descripcion", "%" + descripcion + "%");
		}
		if (estado != null) {
			textoQuery += "p.estado = :estado AND ";
			params.put("estado", estado);
		}
		if (precio > 0) {
			textoQuery += "p.precio = :precio";
			params.put("descripcion", precio);
		}
		TypedQuery<Producto> query = em.createQuery(textoQuery, Producto.class);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

}
