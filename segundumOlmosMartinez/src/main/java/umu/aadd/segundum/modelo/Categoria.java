package umu.aadd.segundum.modelo;

import java.util.Collections;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import repositorio.Identificable;

/**
 * Clase que representa una categoría de SegundUM.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name = "categorias")
@NamedNativeQuery(name = "Categoria.getRaices", query = "SELECT * FROM categorias WHERE padre_id IS NULL", resultClass = Categoria.class)
public class Categoria implements Identificable {

	/**
	 * Identificador único de la categoría.
	 */
	@XmlAttribute
	@Id
	private String id;

	/**
	 * Nombre de la categoría.
	 */
	@XmlElement
	@Column(name = "nombre", nullable = false, updatable = false)
	private String nombre;

	/**
	 * Descripción de la categoría.
	 */
	@XmlTransient
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	/**
	 * Ruta de la categoría.
	 */
	@XmlAttribute
	@Lob
	@Column(name = "ruta", nullable = false, updatable = false)
	private String ruta;

	/**
	 * Subcategorías de la categoría.
	 */
	@XmlElement(name = "categoria")
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "padre_id")
	private List<Categoria> subcategorias;

	/**
	 * Constructor vacío de la clase Categoria, requerido por JPA.
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
	 * Recupera el nombre de la categoría.
	 * 
	 * @return Nombre de la categoría.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Recupera la descripción de la categoría.
	 * 
	 * @return Descripción de la categoría.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion de la categoria.
	 * 
	 * @param descripcion Descripción de la categoria.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera la ruta de la categoría.
	 * 
	 * @return Ruta de la categoría.
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Recupera las subcategorías de la categoría.
	 * 
	 * @return Subcategorías de la categoría.
	 */
	public List<Categoria> getSubcategorias() {
		return Collections.unmodifiableList(subcategorias);
	}

	/**
	 * Recupera los nombres de las subcategorías de la categoría.
	 * 
	 * @return Nombres de las subcategorías de la categoría.
	 */
	private String getNombresSubCategorias() {
		String nombres = "{";
		for (Categoria subcategoria : subcategorias) {
			nombres += subcategoria.getNombre();
			if (!subcategoria.equals(subcategorias.get(subcategorias.size() - 1))) {
				nombres += ", ";
			}
		}
		nombres += "}";
		return nombres;
	}
	
	/**
	 * Calcula el nivel de profundidad de la categoría dentro de la jerarquía,
	 * a partir del número de identificadores en su ruta.
	 * 
	 * @return Nivel de profundidad (0 para categorías raíz).
	 */
	public int getNivel() {
		return (int) ruta.chars().filter(ch -> ch == '|').count() - 2;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", ruta=" + ruta + ", subcategorias=" + getNombresSubCategorias() + "]";
	}
}
