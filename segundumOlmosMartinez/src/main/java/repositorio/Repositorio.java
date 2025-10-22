package repositorio;

import java.util.List;

/**
 *  Repositorio para entidades gestionadas con identificador.
 *  @param T representa el tipo de datos de la entidad.
 *  @param K es el tipo del identificador.
 */
public interface Repositorio<T, K> {

	/**
	 * Añade una nueva entidad al repositorio.
	 * @param entity La entidad a añadir.
	 * @return El identificador de la entidad añadida.
	 * @throws RepositorioException Si ocurre un error al añadir la entidad.
	 */
	K add(T entity) throws RepositorioException;

	/**
	 * Actualiza una entidad existente en el repositorio.
	 * @param entity La entidad a actualizar.
	 * @throws RepositorioException Si ocurre un error al actualizar la entidad.
	 * @throws EntidadNoEncontrada Si la entidad no se encuentra en el repositorio.
	 */
	void update(T entity) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Elimina una entidad del repositorio.
	 * @param entity La entidad a eliminar.
	 * @throws RepositorioException Si ocurre un error al eliminar la entidad.
	 * @throws EntidadNoEncontrada Si la entidad no se encuentra en el repositorio.
	 */
	void delete(T entity) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera una entidad por su identificador.
	 * @param id El identificador de la entidad.
	 * @return La entidad correspondiente al identificador.
	 * @throws RepositorioException Si ocurre un error al recuperar la entidad.
	 * @throws EntidadNoEncontrada Si la entidad no se encuentra en el repositorio.
	 */
	T getById(K id) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera todas las entidades del repositorio.
	 * @return Una lista con todas las entidades.
	 * @throws RepositorioException Si ocurre un error al recuperar las entidades.
	 */
	List<T> getAll() throws RepositorioException;

	/**
	 * Recupera una los identificadores de todas las entidades en el repositorio.
	 * @return Todos los identificadores.
	 * @throws RepositorioException Si ocurre un error al recuperar los identificadores.
	 */
	List<K> getIds() throws RepositorioException;

}