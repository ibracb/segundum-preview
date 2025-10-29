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
	 * @param ruta Ruta a partir de la cual se recupera la jerarquía de categorías.
	 */
	public void cargarJerarquiaCategorias(String ruta) throws JAXBException, RepositorioException;
	
	/**
	 * Modifica el texto de una categoría existente.
	 * 
	 * @param idCategoria Identificador de la categoría a modificar.
	 * @param descripcionNueva  Nueva descripción para la categoría.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada Si no se encuentra la categoría con el identificador proporcionado.
	 */
	public void modificarCategoria(String idCategoria, String descripcionNueva) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera el conjunto de categorías raíz (sin categoría padre).
	 * 
	 * @return Conjunto de categorías raíz.
	 */
	public List<Categoria> recuperarCategoriasRaiz() throws RepositorioException;
	
	/**
	 * Recupera los descendientes de una categoría específica.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return Conjunto de categorías descendientes.
	 */
	public List<Categoria> recuperarDescendientesCategoria(String idCategoria) throws RepositorioException;
	
	/**
	 * Recupera una categoría por su identificador.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return La categoría correspondiente al identificador proporcionado.
	 * @throws RepositorioException Si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada Si no se encuentra la categoría con el identificador proporcionado.
	 */
	public Categoria recuperarCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada;
	
}
