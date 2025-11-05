package umu.aadd.segundum.servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Usuario;

/**
 * Interfaz del servicio de gestión de usuarios.
 */
public interface IServicioUsuarios {
	
	/**
	 * Da de alta un nuevo usuario en el sistema.
	 * 
	 * @param nombre          Nombre del usuario.
	 * @param apellidos       Apellidos del usuario.
	 * @param email           Correo electrónico del usuario.
	 * @param clave           Clave de acceso del usuario.
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 * @param telefono        Teléfono de contacto del usuario.
	 * @return Identificador único del usuario creado.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	public String altaUsuario(String nombre, String apellidos, String email, String clave, String fechaNacimiento, String telefono) throws RepositorioException;
	
	/**
	 * Modifica los datos de un usuario existente.
	 * 
	 * @param idUsuario       Identificador del usuario a modificar.
	 * @param nombre          Nuevo nombre del usuario.
	 * @param apellidos       Nuevos apellidos del usuario.
	 * @param clave           Nueva clave de acceso del usuario.
	 * @param fechaNacimiento Nueva fecha de nacimiento del usuario.
	 * @param telefono        Nuevo teléfono de contacto del usuario.
	 * @param administrador   Nuevo estado de administrador del usuario.
	 * @throws RepositorioException    Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada     Si el usuario con el identificador especificado no existe.
	 */
	public void modificarUsuario(String idUsuario, String nombre, String apellidos, String clave, String fechaNacimiento, String telefono, Boolean administrador) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera un usuario por su identificador.
	 * 
	 * @param idUsuario Identificador del usuario a recuperar.
	 * @return Usuario correspondiente al identificador especificado.
	 * @throws RepositorioException    Si ocurre un error al acceder al repositorio.
	 * @throws EntidadNoEncontrada     Si el usuario con el identificador especificado no existe.
	 */
	public Usuario recuperarUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada;
		
}
