package umu.aadd.segundum.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class UsuarioDTO implements Serializable {
	
	/**
	 * Identificador único del usuario DTO.
	 */
	private String id;
	
	/**
	 * Email del usuario DTO.
	 */
	private String email;
	
	/**
	 * Nombre del usuario DTO.
	 */
	private String nombre;
	
	/**
	 * Apellidos del usuario DTO.
	 */
	private String apellidos;
	
	/**
	 * Fecha de nacimiento del usuario DTO.
	 */
	private LocalDate fechaNacimiento;
	
	/**
	 * Teléfono del usuario DTO.
	 */
	private String telefono;
	
	/**
	 * Indica si el usuario DTO es administrador.
	 */
	private boolean administrador;

	/**
	 * Constructor de la clase UsuarioDTO.
	 * 
	 * @param id               Identificador único del usuario DTO.
	 * @param email            Email del usuario DTO.
	 * @param nombre           Nombre del usuario DTO.
	 * @param apellidos        Apellidos del usuario DTO.
	 * @param fechaNacimiento  Fecha de nacimiento del usuario DTO.
	 * @param telefono         Teléfono del usuario DTO.
	 * @param administrador    Indica si el usuario DTO es administrador.
	 */
	public UsuarioDTO(String id, String email, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, boolean administrador) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.administrador = administrador;
	}
	
	/**
	 * Recupera el identificador único del usuario DTO.
	 * 
	 * @return Identificador único del usuario DTO.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Recupera el email del usuario DTO.
	 * 
	 * @return Email del usuario DTO.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Recupera el nombre del usuario DTO.
	 * 
	 * @return Nombre del usuario DTO.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario DTO.
	 * 
	 * @param nombre Nombre del usuario DTO.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Recupera los apellidos del usuario DTO.
	 * 
	 * @return Apellidos del usuario DTO.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del usuario DTO.
	 * 
	 * @param apellidos Apellidos del usuario DTO.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Recupera la fecha de nacimiento del usuario DTO.
	 * 
	 * @return Fecha de nacimiento del usuario DTO.
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del usuario DTO.
	 * 
	 * @param fechaNacimiento Fecha de nacimiento del usuario DTO.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Recupera el teléfono del usuario DTO.
	 * 
	 * @return Teléfono del usuario DTO.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono del usuario DTO.
	 * 
	 * @param telefono Teléfono del usuario DTO.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Indica si el usuario DTO es administrador.
	 * 
	 * @return true si el usuario DTO es administrador, false en caso contrario.
	 */
	public boolean isAdministrador() {
		return administrador;
	}

	/**
	 * Establece si el usuario DTO es administrador.
	 * 
	 * @param administrador true si el usuario DTO es administrador, false en caso contrario.
	 */
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}
