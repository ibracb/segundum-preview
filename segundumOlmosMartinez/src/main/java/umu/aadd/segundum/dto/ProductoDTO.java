package umu.aadd.segundum.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import umu.aadd.segundum.modelo.LugarRecogida;

/**
 * Clase que modela un Data Transfer Object (DTO) para un producto.
 */
@SuppressWarnings("serial")
public class ProductoDTO implements Serializable {

	/**
	 * Identificador único del producto DTO.
	 */
	private String id;
	
	/**
	 * Título del producto DTO.
	 */
	private String titulo;
	
	/**
	 * Descripción del producto DTO.
	 */
	private String descripcion;
	
	/**
	 * Precio del producto DTO.
	 */
	private double precio;
	
	/**
	 * Estado del producto DTO.
	 */
	private String estado;
	
	/**
	 * Fecha de publicación del producto DTO.
	 */
	private LocalDateTime fechaPublicacion;
	
	/**
	 * Categoría del producto DTO.
	 */
	private String categoria;
	
	/**
	 * Número de visualizaciones del producto DTO.
	 */
	private long visualizaciones;
	
	/**
	 * Indica si el envío está disponible para el producto DTO.
	 */
	private boolean envioDisponible;
	
	/**
	 * Descripción del lugar de recogida del producto DTO.
	 */
	private LugarRecogida lugarRecogida;
	
	/**
	 * Vendedor del producto DTO.
	 */
	private String vendedor;

	/**
	 * Construye un ProductoDTO con los atributos especificados.
	 * 
	 * @param id                        Identificador único del producto DTO.
	 * @param titulo                    Título del producto DTO.
	 * @param descripcion               Descripción del producto DTO.
	 * @param precio                    Precio del producto DTO.
	 * @param estado                    Estado del producto DTO.
	 * @param fechaPublicacion          Fecha de publicación del producto DTO.
	 * @param categoria                 Categoría del producto DTO.
	 * @param visualizaciones           Número de visualizaciones del producto DTO.
	 * @param envioDisponible           Indica si el envío está disponible para el producto DTO.
	 * @param lugarRecogida  			Lugar de recogida del producto DTO.
	 * @param vendedor                  Vendedor del producto DTO.
	 */
	public ProductoDTO(String id, String titulo, String descripcion, double precio, String estado,
			LocalDateTime fechaPublicacion, String categoria, long visualizaciones, boolean envioDisponible,
			LugarRecogida lugarRecogida, String vendedor) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.fechaPublicacion = fechaPublicacion;
		this.categoria = categoria;
		this.visualizaciones = visualizaciones;
		this.envioDisponible = envioDisponible;
		this.lugarRecogida = lugarRecogida;
		this.vendedor = vendedor;
	}

	/**
	 * Recupera el identificador único del producto DTO.
	 * 
	 * @return Identificador único del producto DTO.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Recupera el título del producto DTO.
	 * 
	 * @return Título del producto DTO.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Recupera la descripción del producto DTO.
	 * 
	 * @return Descripción del producto DTO.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del producto DTO.
	 * 
	 * @param descripcion Nueva descripción del producto DTO.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera el precio del producto DTO.
	 * 
	 * @return Precio del producto DTO.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto DTO.
	 * 
	 * @param precio Nuevo precio del producto DTO.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Recupera el estado del producto DTO.
	 * 
	 * @return Estado del producto DTO.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Recupera la fecha de publicación del producto DTO.
	 * 
	 * @return Fecha de publicación del producto DTO.
	 */
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	public String getFechaFormateada() {
	    if (fechaPublicacion != null) {
	        return fechaPublicacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    }
	    return "";
	}

	/**
	 * Recupera la categoría del producto DTO.
	 * 
	 * @return Categoría del producto DTO.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Recupera el número de visualizaciones del producto DTO.
	 * 
	 * @return Número de visualizaciones del producto DTO.
	 */
	public long getVisualizaciones() {
		return visualizaciones;
	}

	/**
	 * Establece el número de visualizaciones del producto DTO.
	 * 
	 * @param visualizaciones Nuevo número de visualizaciones del producto DTO.
	 */
	public void setVisualizaciones(long visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	/**
	 * Indica si el envío está disponible para el producto DTO.
	 * 
	 * @return true si el envío está disponible, false en caso contrario.
	 */
	public boolean isEnvioDisponible() {
		return envioDisponible;
	}

	/**
	 * Recupera la descripción del lugar de recogida del producto DTO.
	 * 
	 * @return Descripción del lugar de recogida del producto DTO.
	 */
	public LugarRecogida getLugarRecogida() {
		return lugarRecogida;
	}
	
	/**
	 * Establece el lugar de recogida del producto DTO.
	 * 
	 * @param lugarRecodiga Nuevo lugar de recogida del producto DTO.
	 */
	public void setLugarRecogida(LugarRecogida lugarRecodiga) {
		lugarRecogida = lugarRecodiga;
	}

	/**
	 * Recupera la descripción del lugar de recogida del producto DTO.
	 * 
	 * @return Descripción del lugar de recogida del producto DTO.
	 */
	public String getDescripcionLugarRecogida() {
		return lugarRecogida.getDescripcion();
	}
	
	/**
	 * Establece la descripción del lugar de recogida del producto DTO.
	 * 
	 * @param descripcionLugarRecodiga Nueva descripción del lugar de recogida del producto DTO.
	 */
	public void setDescripcionLugarRecogida(String descripcionLugarRecodiga) {
		lugarRecogida.setDescripcion(descripcionLugarRecodiga);;
	}
	
	/**
	 * Recupera la longitud del lugar de recogida del producto DTO.
	 * 
	 * @return Longitud del lugar de recogida del producto DTO.
	 */
	public Double getLongitudLugarRecogida() {
		return lugarRecogida.getLongitud();
	}
	
	/**
	 * Establece la longitud del lugar de recogida del producto DTO.
	 * 
	 * @param longitudLugarRecodiga Nueva longitud del lugar de recogida del producto DTO.
	 */
	public void setLongitudLugarRecogida(Double longitudLugarRecodiga) {
		lugarRecogida.setLongitud(longitudLugarRecodiga);
	}
	
	/**
	 * Recupera la latitud del lugar de recogida del producto DTO.
	 * 
	 * @return Latitud del lugar de recogida del producto DTO.
	 */
	public Double getLatitudLugarRecogida() {
		return lugarRecogida.getLatitud();
	}
	
	public void setLatitudLugarRecogida(Double latitudLugarRecodiga) {
		lugarRecogida.setLatitud(latitudLugarRecodiga);
	}
	
	
	/**
	 * Recupera el vendedor del producto DTO.
	 * 
	 * @return Vendedor del producto DTO.
	 */
	public String getVendedor() {
		return vendedor;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", estado=" + estado + ", fechaPublicacion=" + fechaPublicacion.toString() + ", categoria=" + categoria
				+ ", visualizaciones=" + visualizaciones + ", envioDisponible=" + envioDisponible
				+ ", descripcionLugarRecogida=" + lugarRecogida.getDescripcion() + ", vendedor=" + vendedor + "]";
	}
	
}
