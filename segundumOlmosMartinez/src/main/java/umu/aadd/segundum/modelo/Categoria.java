package umu.aadd.segundum.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import repositorio.Identificable;

@XmlRootElement
@Entity
@Table(name = "categorias")
public class Categoria implements Identificable {
	
	/**
	 * Identificador único de la categoría.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * Nombre de la categoría.
	 */
	@Column(name = "nombre", nullable = false, updatable = false)
	private String nombre;
	
	/**
	 * Descripción de la categoría.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	/**
	 * Ruta de la categoría.
	 */
	@Column(name = "ruta", nullable = false, unique = true, updatable = false)
	private String ruta;
	
	/**
	 * Subcategorías de la categoría.
	 */
	@OneToMany(cascade = CascadeType.PERSIST)	//preguntar: ¿si elimino un padre, debería eliminar los hijos? si sí, cambiar a ALL
	@JoinColumn(name = "categoria_padre_id")
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
	@XmlAttribute
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
	@XmlTransient
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
	@XmlAttribute
	public String getRuta() {
		return ruta;
	}

	/**
	 * Recupera las subcategorías de la categoría.
	 * 
	 * @return Subcategorías de la categoría.
	 */
	@XmlAttribute(name = "categoria")
	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	/**
	 * Establece la subcategorías de la categoria.
	 * 
	 * @param subcategorias Subcategorías de la categoría.
	 */
	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

}
