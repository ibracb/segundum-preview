package umu.aadd.segundum.modelo;

import javax.persistence.Embeddable;

/**
 * Clase que modela un lugar de recogida de un producto en SegundUM.
 */
@Embeddable
public class LugarRecogida {
	
	/**
	 * Descripcion del lugar de recogida.
	 */
	private String descripcion;
	
	/**
	 * Longitud del lugar de recogida.
	 */
	private double longitud;
	
	/**
	 * Latitud del lugar de recogida.
	 */
	private double latitud;

	/**
	 * Construye un producto.
	 * 
	 * @param descripcion Descripcion del lugar de recogida.
	 * @param longitud    Longitud del lugar de recodiga.
	 * @param latitud     Latitud del lugar de recodiga.
	 */
	public LugarRecogida(String descripcion, double longitud, double latitud) {
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	/**
	 * Constructor vacío de la clase LugarRecogida, requerido por JPA.
	 */
	protected LugarRecogida() {
	}

	/**
	 * Recupera la descripcion del lugar de recogida.
	 * 
	 * @return Descripcion del lugar de recogida.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Recupera la longitud del lugar de recogida
	 * 
	 * @return Longitud del lugar de recogida.
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Recupera la longitud del lugar de recogida.
	 * 
	 * @return Latitud del lugar de recogida.
	 */
	public double getLatitud() {
		return latitud;
	}
}
