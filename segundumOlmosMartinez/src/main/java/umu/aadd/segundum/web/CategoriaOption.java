package umu.aadd.segundum.web;

/**
 * Clase de presentación para mostrar una categoría en un desplegable,
 * con su nombre ya formateado según su nivel jerárquico.
 */
public class CategoriaOption {

	private String id;
	private String nombreIndentado;

	public CategoriaOption(String id, String nombreIndentado) {
		this.id = id;
		this.nombreIndentado = nombreIndentado;
	}

	public String getId() {
		return id;
	}

	public String getNombreIndentado() {
		return nombreIndentado;
	}
}