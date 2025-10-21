package umu.aadd.segundum.modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import repositorio.Identificable;

@Entity
public class Categoria implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@Column(name = "ruta", nullable = false)
	private String ruta;

	@Column(name = "subcategoria", nullable = true)
	private LinkedList<Categoria> subcategorias;

	/**
	 * Construye un usuario con los datos especificados, sin teléfono.
	 * @param ruta          Ruta del fichero xml.
	 */
	public Categoria(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Constructor vacío de la clase Usuario, requerido por JPA.
	 */
	protected Categoria() {
	}

	/**
	 * Recupera el identificador de la categoría.
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Establece el identificador de la categoria.
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Recupera la descripcion de la categoría.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion de la categoria.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera la ruta de la categoría.
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Recupera la subcategoria de la categoría.
	 */
	public String getSubcategoria() {
		return descripcion;
	}

	/**
	 * Establece la subcategoria de la categoria.
	 */
	public void setSubcategoria(Categoria subcategorias) {
		this.subcategorias = subcategorias;
	}

}
