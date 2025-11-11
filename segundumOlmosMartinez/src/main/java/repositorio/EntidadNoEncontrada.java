package repositorio;

/**
 * Excepción notificada si no existe una entidad con el identificador
 * proporcionado en el repositorio.
 */
@SuppressWarnings("serial")
public class EntidadNoEncontrada extends Exception {

	/**
	 * Construye la excepción con un mensaje determinado.
	 * 
	 * @param msg Mensaje de la excepción.
	 */
	public EntidadNoEncontrada(String msg) {
		super(msg);
	}

}
