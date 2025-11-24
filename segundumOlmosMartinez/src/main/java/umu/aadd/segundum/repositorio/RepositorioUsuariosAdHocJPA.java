package umu.aadd.segundum.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Usuario;
import utils.EntityManagerHelper;

/**
 * Repositorio ad-hoc para usuarios utilizando JPA.
 */
public class RepositorioUsuariosAdHocJPA extends RepositorioUsuariosJPA implements RepositorioUsuariosAdHoc {

	@Override
	public Usuario getByEmailAndClave(String email, String clave) throws RepositorioException {
		if(email != null && !email.trim().isEmpty() && !email.trim().isBlank()
				&& clave != null && !clave.trim().isEmpty() && !clave.trim().isBlank()) {
			try {
				EntityManager em = EntityManagerHelper.getEntityManager();
				String textoQuery = "SELECT u FROM Usuario u "
						+ "WHERE u.email = :email "
						+ "AND u.clave = :clave ";
				TypedQuery<Usuario> query = em.createQuery(textoQuery, Usuario.class);
				query.setParameter("email", email);
				query.setParameter("clave", clave);
				query.setMaxResults(1);
				return query.getSingleResult();
			}
			catch(RuntimeException e) {
				throw new RepositorioException("Error al intentar obtener un usuario por email y clave", e);
			}
			finally {
				EntityManagerHelper.closeEntityManager();
			}
		}
		return null;
	}

}