package umu.aadd.segundum.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import utils.EntityManagerHelper;

/**
 * Repositorio ad-hoc para categorías utilizando JPA.
 */
public class RepositorioCategoriasAdHocJPA extends RepositorioCategoriasJPA implements RepositorioCategoriasAdHoc {

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> getRaices() throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			Query query = em.createNamedQuery("Categoria.getRaices");
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			return query.getResultList();
		}
		catch (RuntimeException e) {
			throw new RepositorioException("Error buscando todas las categorías raíz", e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> getDescendientes(String idCategoria) throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			Query query = em.createNamedQuery("Categoria.getDescendientes");
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter(1, idCategoria);
			return query.getResultList();
		}
		catch (RuntimeException e) {
			throw new RepositorioException("Error buscando todas las categorías descendientes", e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

}
