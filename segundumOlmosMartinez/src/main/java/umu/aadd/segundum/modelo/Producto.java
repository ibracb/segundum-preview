package umu.aadd.segundum.modelo;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import repositorio.Identificable;

/**
 * Clase que modela un producto de SegundUM.
 */
@Entity
@Table(name = "productos")
public class Producto implements Identificable {

	/**
	 * Constante de precio gratuito para un producto.
	 */
	public static final double PRECIO_GRATUITO = 0.0;

	/**
	 * Constante que indica cero visualizaciones de un producto.
	 */
	private static final int CERO_VISUALIZACIONES = 0;

	/**
	 * Identificador único del producto.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * Título del producto.
	 */
	@Column(name = "titulo", nullable = false, updatable = false)
	private String titulo;

	/**
	 * Descripción del producto.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	/**
	 * Precio del producto.
	 */
	@Column(name = "precio", nullable = false)
	private double precio;

	/**
	 * Estado del producto.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false, updatable = false)
	private EstadoProducto estado;

	/**
	 * Fecha y hora de la publicación del producto.
	 */
	@Column(name = "fecha_publicacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaPublicacion;

	/**
	 * Categoría del producto.
	 */
	@OneToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	/**
	 * Número de visualizaciones del producto.
	 */
	@Column(name = "visualizaciones", nullable = false)
	private int visualizaciones;

	/**
	 * Disponibilidad de envío del producto.
	 */
	@Column(name = "envio_disponible", nullable = false, updatable = false)
	private boolean envioDisponible;

	/**
	 * Lugar de recogida del producto.
	 */
	@Embedded
	@AttributeOverride(name = "descripcion", column = @Column(name = "descripcion_lugar_recogida"))
	private LugarRecogida recogida;

	/**
	 * Vendedor del producto.
	 */
	@OneToOne
	@JoinColumn(name = "vendedor_id")
	private Usuario vendedor;

	/**
	 * Construye un producto.
	 * 
	 * @param titulo          Titulo del producto.
	 * @param descripcion     Descripcion del producto.
	 * @param precio          Precio del producto.
	 * @param estado          Estado del producto.
	 * @param categoria       Categoria del producto.
	 * @param envioDisponible Si el envio del producto esta disponible o no.
	 * @param vendedor        Vendedor del producto.
	 */
	public Producto(String titulo, String descripcion, double precio, EstadoProducto estado, Categoria categoria,
			boolean envioDisponible, Usuario vendedor) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.fechaPublicacion = LocalDateTime.now();
		this.categoria = categoria;
		this.visualizaciones = CERO_VISUALIZACIONES;
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
	 * Recupera el título del producto.
	 * 
	 * @return Título del producto.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Recupera la descripción del producto.
	 * 
	 * @return Descripción del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del producto.
	 * 
	 * @param descripcion Descripcion del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera el precio del producto.
	 * 
	 * @return Precio del producto.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto.
	 * 
	 * @param precio Precio del producto.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Recupera el estado del producto.
	 * 
	 * @return Estado del producto.
	 */
	public EstadoProducto getEstado() {
		return estado;
	}

	/**
	 * Recupera la fecha y hora de la publicación del producto.
	 * 
	 * @return Fecha y hora de la publicacion del producto.
	 */
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * Recupera la categoría del producto.
	 * 
	 * @return Categoría del producto.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Recupera el número de visualizaciones del producto.
	 * 
	 * @return Número de visualizaciones del producto.
	 */
	public int getVisualizaciones() {
		return visualizaciones;
	}

	/**
	 * Establece el número de visualizaciones del producto.
	 * 
	 * @param visualizaciones Número de visualizaciones del producto.
	 */
	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	/**
	 * Recupera la disponibilidad de envío del producto.
	 * 
	 * @return true si el envío esta disponible, false en caso contrario.
	 */
	public boolean getEnvioDisponible() {
		return envioDisponible;
	}

	/**
	 * Recupera el lugar de recogida del producto.
	 * 
	 * @return Lugar de recogida del producto.
	 */
	public LugarRecogida getRecogida() {
		return recogida;
	}

	/**
	 * Establece el lugar de recogida del producto.
	 * 
	 * @param recogida Lugar de recogida del producto.
	 */
	public void setRecogida(LugarRecogida recogida) {
		this.recogida = recogida;
	}

	/**
	 * Recupera el vendedor del producto.
	 * 
	 * @return Vendedor del producto.
	 */
	public Usuario getVendedor() {
		return vendedor;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", estado=" + estado + ", fechaPublicacion=" + fechaPublicacion + ", categoria=" + categoria.getNombre()
				+ ", visualizaciones=" + visualizaciones + ", envioDisponible=" + envioDisponible + ", recogida="
				+ recogida + ", vendedor=" + vendedor.getNombre() + " " + vendedor.getApellidos() + "]";
	}
	
}
