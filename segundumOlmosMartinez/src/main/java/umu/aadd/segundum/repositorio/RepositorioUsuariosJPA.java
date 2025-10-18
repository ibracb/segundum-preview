package umu.aadd.segundum.repositorio;

import repositorio.RepositorioJPA;
import umu.aadd.segundum.modelo.Usuario;

/**
 * Repositorio JPA para la entidad Usuario.
 */
public class RepositorioUsuariosJPA extends RepositorioJPA<Usuario> {

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Devuelve la clase de la entidad Usuario.
	 * </p>
	 */
	@Override
	public Class<Usuario> getClase() {
		return Usuario.class;
	}

}
