package umu.aadd.segundum.repositorio;

import repositorio.RepositorioException;
import repositorio.RepositorioString;
import umu.aadd.segundum.modelo.Usuario;

/**
 * Repositorio ad-hoc para usuarios.
 */
public interface RepositorioUsuariosAdHoc extends RepositorioString<Usuario> {
	
	/**
	 * Obtiene un usuario por su email y clave.
	 * 
	 * @param email Email del usuario.
	 * @param clave Clave del usuario.
	 * @return Usuario que coincide con el email y clave proporcionados.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	Usuario getByEmailAndClave(String email, String clave) throws RepositorioException;
	
}
