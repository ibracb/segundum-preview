package repositorio;

/**
 * Interfaz que define los métodos para obtener y establecer un identificador único en una entidad.
 */
public interface Identificable {

	/**
	 * Recupera el identificador único de la entidad.
	 * @return El identificador único.
	 */
	String getId();
	
	/**
	 * Establece el identificador único de la entidad.
	 * @param id El identificador único a establecer.
	 */
	void setId(String id);
}
