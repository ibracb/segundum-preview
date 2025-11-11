package umu.aadd.segundum.servicio;

import java.util.List;

import javax.xml.bind.JAXBException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;

public interface IServicioCategorias {

	/**
	 * Carga la jerarquía de categorías a partir de la ruta especificada.
	 * 
	 * @param idUsuario Identificador del usuario que realiza la operación.
	 * @param ruta      Ruta a partir de la cual se recupera la jerarquía de
	 *                  categorías.
	 * @throws EntidadNoEncontrada Si no se encuentra el usuario con el
	 *                             identificador proporcionado.
	 */
	public void cargarJerarquiaCategorias(String idUsuario, String ruta)
			throws JAXBException, RepositorioException, EntidadNoEncontrada;

	/**
	 * Modifica el texto de una categoría existente.
	 * 
	 * @param idCategoria Identificador de la categoría a modificar.
	 * @param descripcion Nueva descripción para la categoría.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra la categoría con el
	 *                              identificador proporcionado.
	 */
	public void modificarCategoria(String idUsuario, String idCategoria, String descripcion)
			throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera el conjunto de categorías raíz (sin categoría padre).
	 * 
	 * @return Conjunto de categorías raíz.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 */
	public List<Categoria> recuperarCategoriasRaiz() throws RepositorioException;

	/**
	 * Recupera los descendientes de una categoría específica.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return Conjunto de categorías descendientes.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 */
	public List<Categoria> recuperarDescendientesCategoria(String idCategoria) throws RepositorioException;

	/**
	 * Recupera una categoría por su identificador.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return La categoría correspondiente al identificador proporcionado.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada  Si no se encuentra la categoría con el
	 *                              identificador proporcionado.
	 */
	public Categoria recuperarCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Recupera todas las categorías disponibles.
	 * 
	 * @return Lista de todas las categorías.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 */
	public List<Categoria> recuperarTodasCategorias() throws RepositorioException;

}
