package umu.aadd.segundum.modelo;

import javax.persistence.Embeddable;

/**
 * Clase que modela un lugar de recogida de un producto en SegundUM.
 */
@Embeddable
public class LugarRecogida {

	/**
	 * Constante de longitud mínima válida.
	 */
	public static final double LONGITUD_MINIMA = -180.0;

	/**
	 * Constante de longitud máxima válida.
	 */
	public static final double LONGITUD_MAXIMA = 180.0;

	/**
	 * Constante de latitud mínima válida.
	 */
	public static final double LATITUD_MINIMA = -90.0;

	/**
	 * Constante de latitud máxima válida.
	 */
	public static final double LATITUD_MAXIMA = 90.0;

	/**
	 * Descripcion del lugar de recogida.
	 */
	//@Lob
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
