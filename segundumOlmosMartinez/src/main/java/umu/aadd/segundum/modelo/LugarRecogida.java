package umu.aadd.segundum.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;

/**
 * Clase que modela un lugar de recogida de un producto en SegundUM.
 */
public class LugarRecogida {
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "longitud", nullable = false)
	private double longitud;

	@Column(name = "latitud", nullable = false)
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
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Recupera la longitud del lugar de recogida.
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Recupera la longitud del lugar de recogida.
	 */
	public double getLatitud() {
		return latitud;
	}
}
