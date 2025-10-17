package segundum.servicio;

import java.time.LocalDate;

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
	 */
	public String altaUsuario(String nombre, String apellidos, String email, String clave, LocalDate fechaNacimiento, String telefono);
	
	/**
	 * Modifica los datos de un usuario existente.
	 * 
	 * @param idUsuario       Identificador del usuario a modificar.
	 * @param nombre          Nuevo nombre del usuario.
	 * @param apellidos       Nuevos apellidos del usuario.
	 * @param clave           Nueva clave de acceso del usuario.
	 * @param fechaNacimiento Nueva fecha de nacimiento del usuario.
	 * @param telefono        Nuevo teléfono de contacto del usuario.
	 */
	public void modificarUsuario(String idUsuario, String nombre, String apellidos, String clave, LocalDate fechaNacimiento, String telefono);
	
}
