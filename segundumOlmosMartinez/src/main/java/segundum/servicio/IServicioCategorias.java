package segundum.servicio;

import java.util.Set;

import segundum.modelo.Categoria;

public interface IServicioCategorias {
	
	/**
	 * Carga la jerarquía de categorías desde un fichero XML en la ruta especificada.
	 * 
	 * @param ruta Ruta del fichero XML que contiene la jerarquía de categorías.
	 */
	public void cargarJerarquiaCategorias(String ruta);
	
	/**
	 * Modifica el texto de una categoría existente.
	 * 
	 * @param idCategoria Identificador de la categoría a modificar.
	 * @param textoNuevo  Nuevo texto para la categoría.
	 */
	public void modificarCategoria(String idCategoria, String textoNuevo);
	
	/**
	 * Recupera el conjunto de categorías raíz (sin categoría padre).
	 * 
	 * @return Conjunto de categorías raíz.
	 */
	public Set<Categoria> recuperarCategoriasRaiz();
	
	/**
	 * Recupera los descendientes de una categoría específica.
	 * 
	 * @param idCategoria Identificador de la categoría.
	 * @return Conjunto de categorías descendientes.
	 */
	public Set<Categoria> recuperarDescendientesCategoria(String idCategoria);
	
	
}
