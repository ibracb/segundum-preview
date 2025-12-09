package umu.aadd.segundum.repositorio;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import repositorio.RepositorioException;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

/**
 * Implementación JPA del repositorio ad-hoc para productos.
 */
public class RepositorioProductosAdHocJPA extends RepositorioProductosJPA implements RepositorioProductosAdHoc {

	/**
	 * EntityManagerFactory como factoría para crear EntityManager paara segundum.
	 */
	@PersistenceContext
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("segundum");

	/**
	 * EntityManager para interactuar con la base de datos.
	 */
	EntityManager em = emf.createEntityManager();

	@Override
	public List<Producto> recuperarHistorial(Month mes, int anio) throws RepositorioException {
		try {
			return em.createQuery(
					"SELECT p FROM Producto p WHERE FUNCTION('MONTH', p.fechaPublicacion) = :mes AND FUNCTION('YEAR', p.fechaPublicacion) = :anio ORDER BY p.visualizaciones DESC",
					Producto.class).setParameter("mes", mes.getValue()).setParameter("anio", anio).getResultList();
			
		} catch (Exception e) {
			throw new RepositorioException("Error al recuperar el historial", e);
		}
	}

	@Override
	public List<Producto> recuperarProductosVenta(List<Categoria> categorias, String descripcion, EstadoProducto estado,
			double precio) throws RepositorioException {
		Map<String, Object> params = new HashMap<>();

		String textoQuery = "SELECT p FROM Producto p WHERE 1=1";

		if (categorias != null && !categorias.isEmpty()) {
			textoQuery += " AND p.categoria IN :categorias";
			params.put("categorias", categorias);
		}
		if ((descripcion != null && !descripcion.isEmpty()) || (descripcion != null && descripcion.equals(" "))) {
			textoQuery += " AND p.descripcion LIKE :descripcion";
			params.put("descripcion", "%" + descripcion + "%");
		}
		if (estado != null) {
			textoQuery += " AND p.estado IN :estadosPermitidos";
			params.put("estadosPermitidos", getEstadosIgualesOMejores(estado));
		}
		if (precio >= Producto.PRECIO_GRATUITO) {
			textoQuery += " AND p.precio <= :precio";
			params.put("precio", precio);
		}
		try {
			TypedQuery<Producto> query = em.createQuery(textoQuery, Producto.class);
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new RepositorioException("Error al recuperar los productos en venta", e);
		}

	}

	/**
	 * Obtiene la lista de estados que son iguales o mejores que el estado dado. El
	 * orden de calidad es: NUEVO > COMO_NUEVO > BUEN_ESTADO > ACEPTABLE >
	 * PARA_PIEZAS > REPARAR
	 * 
	 * @param estadoMinimo el estado mínimo requerido
	 * @return lista de estados permitidos
	 */
	private List<EstadoProducto> getEstadosIgualesOMejores(EstadoProducto estadoMinimo) {
		List<EstadoProducto> estadosPermitidos = new ArrayList<>();
		EstadoProducto[] estados = EstadoProducto.values();
		for (EstadoProducto estado : estados) {
			estadosPermitidos.add(estado);
			if (estado == estadoMinimo) {
				break;
			}
		}
		return estadosPermitidos;
	}

	@Override
	public List<Producto> recuperarProductosVentaPropios(UsuarioDTO usuario) throws RepositorioException {
		try {
			return em.createQuery(
					"SELECT p FROM Producto p WHERE p.vendedor.id = :idUsuario",Producto.class).setParameter("idUsuario",usuario.getId()).getResultList();
		} catch (Exception e) {
			throw new RepositorioException("Error al recuperar el historial", e);
		}
	}
	
}
