package umu.aadd.segundum.servicio;

import java.util.List;

import javax.xml.bind.JAXBException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;

public interface IServicioCategorias {
	
	/**
	 * Carga la jerarquía de categorías desde un fichero XML en la ruta especificada.
	 * 
	 * @param ruta Ruta del fichero XML que contiene la jerarquía de categorías.
	 */
	public void cargarJerarquiaCategorias(String ruta) throws JAXBException, RepositorioException;
	
	/**
	 * Modifica el texto de una categoría existente.
	 * 
	 * @param idCategoria Identificador de la categoría a modificar.
	 * @param descripcionNueva  Nueva descripción para la categoría.
	 */
	public void modificarCategoria(String idCategoria, String descripcionNueva) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera el conjunto de categorías raíz (sin categoría padre).
	 * 
	 * @return Conjunto de categorías raíz.
	 */
	public List<Categoria> recuperarCategoriasRaiz();
	
	/**
	 * Recupera los descendientes de una categoría específica.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return Conjunto de categorías descendientes.
	 */
	public List<Categoria> recuperarDescendientesCategoria(String idCategoria);
	
	
}
