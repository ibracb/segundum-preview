package umu.aadd.segundum.repositorio;

import java.util.List;

import repositorio.RepositorioException;
import repositorio.RepositorioString;
import umu.aadd.segundum.modelo.Categoria;

public interface RepositorioCategoriasAdHoc extends RepositorioString<Categoria> {
	
	/**
	 * Obtiene las categorías raíz (sin categoría padre).
	 * 
	 * @return Lista de categorías raíz.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	public List<Categoria> getRaices()  throws RepositorioException;
	
	/**
	 * Obtiene los descendientes de una categoría dada.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return Lista de categorías descendientes.
	 * @throws RepositorioException Si ocurre un error al acceder al repositorio.
	 */
	public List<Categoria> getDescendientes(String idCategoria) throws RepositorioException;
	
}
