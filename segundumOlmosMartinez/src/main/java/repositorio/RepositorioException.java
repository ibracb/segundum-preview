package repositorio;

/**
 * Excepción que representa un fallo en el sistema de persistencia.
 * Al instanciarla, se establece la excepción interna que produce el error (causa).
 */
@SuppressWarnings("serial")
public class RepositorioException extends Exception {

	/**
	 * Construye una excepción de repositorio con un mensaje y una causa.
	 * 
	 * @param msg Mensaje de la excepción.
	 * @param causa Causa de la excepción.
	 */
	public RepositorioException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	/**
	 * Construye una excepción de repositorio con un mensaje.
	 * 
	 * @param msg Mensaje de la excepción.
	 */
	public RepositorioException(String msg) {
		super(msg);		
	}
	
		
}
