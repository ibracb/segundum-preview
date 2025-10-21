package umu.aadd.segundum.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import repositorio.Identificable;

/**
 * Clase que modela un producto de SegundUM.
 */
@Entity
public class Producto implements Identificable {

	/**
	 * Identificador único del producto.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "precio", nullable = false)
	private double precio;

	@Column(name = "estado", nullable = false)
	private EstadoProducto estado;

	@Column(name = "fecha publicacion", nullable = false)
	private LocalDateTime fechaPublicacion;

	@Column(name = "categoria", nullable = false)
	private Categoria categoria;

	@Column(name = "visualizaciones", nullable = false)
	private int visualizaciones;

	@Column(name = "envio disponible", nullable = false)
	private boolean envioDisponible;

	@Column(name = "recogida", nullable = true)
	private LugarRecogida recogida;

	@Column(name = "vendedor", nullable = false)
	private Usuario vendedor;

	/**
	 * Construye un producto.
	 * 
	 * @param titulo 			Titulo del producto.
	 * @param descripcion 		Descripcion del producto.
	 * @param precio			Precio del producto.
	 * @param estado			Estado del producto.
	 * @param categoria			Categoria del producto.
	 * @param envioDisponible	Si el envio del producto esta disponible o no.
	 * @param vendedor			Vendedor del producto.
	 */
	public Producto(String titulo, String descripcion, double precio, EstadoProducto estado, Categoria categoria,
			boolean envioDisponible, Usuario vendedor) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.fechaPublicacion = LocalDateTime.now();
		this.categoria = categoria;
		this.visualizaciones = 0;
		this.envioDisponible = envioDisponible;
		this.vendedor = vendedor;
	}

	/**
	 * Constructor vacío de la clase Categoria, requerido por JPA.
	 */
	protected Producto() {
	}

	/**
	 * Recupera el identificador del producto.
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Establece el identificador del producto.
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Recupera el titulo del producto.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Recupera la descripcion del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el descripcion del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera el precio del producto.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Recupera el estado del producto.
	 */
	public EstadoProducto getEstado() {
		return estado;
	}

	/**
	 * Recupera la fecha de publicacion del producto.
	 */
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * Recupera la categoria del producto.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Recupera el numero de visualizacion del producto.
	 */
	public int getVisualizaciones() {
		return visualizaciones;
	}

	/**
	 * Establece el numero de visualizacion del producto.
	 */
	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	/**
	 * Recupera si el envio del producto esta disponible.
	 */
	public boolean getEnvioDisponible() {
		return envioDisponible;
	}

	/**
	 * Recupera el lugar de recogida del producto.
	 */
	public LugarRecogida getRecogida() {
		return recogida;
	}

	/**
	 * Establece el lugar de recogida del producto.
	 */
	public void setRecogida(LugarRecogida recogida) {
		this.recogida = recogida;
	}

	/**
	 * Recupera el vendedor del producto.
	 */
	public Usuario getVendedor() {
		return vendedor;
	}
}
