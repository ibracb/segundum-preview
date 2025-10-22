package umu.aadd.segundum.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import repositorio.Identificable;

/**
 * Clase que modela un usuario de SegundUM.
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Identificable {

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * Email del usuario.
	 */
	@Column(name = "email", nullable = false, unique = true, updatable = false)
	private String email;

	/**
	 * Nombre del usuario.
	 */
	@Column(name = "nombre", nullable = false)
	private String nombre;

	/**
	 * Apellidos del usuario.
	 */
	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	/**
	 * Clave de acceso del usuario.
	 */
	@Column(name = "clave", nullable = false)
	private String clave;

	/**
	 * Fecha de nacimiento del usuario.
	 */
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaNacimiento;

	/**
	 * Teléfono del usuario.
	 */
	@Column(name = "telefono", unique = true)
	private String telefono;

	/**
	 * Indica si el usuario es administrador.
	 */
	@Column(name = "administrador", nullable = false)
	private boolean administrador;

	/**
	 * Construye un usuario con los datos especificados.
	 * 
	 * @param email           Email del usuario.
	 * @param nombre          Nombre del usuario.
	 * @param apellidos       Apellidos del usuario.
	 * @param clave           Clave de acceso del usuario.
	 * @param telefono        Teléfono del usuario.
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 */
	public Usuario(String email, String nombre, String apellidos, String clave, String telefono,
			LocalDate fechaNacimiento) {
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.clave = clave;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.administrador = false;
	}

	/**
	 * Construye un usuario con los datos especificados, sin teléfono.
	 * 
	 * @param email           Email del usuario.
	 * @param nombre          Nombre del usuario.
	 * @param apellidos       Apellidos del usuario.
	 * @param clave           Clave de acceso del usuario.
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 */
	public Usuario(String email, String nombre, String apellidos, String clave, LocalDate fechaNacimiento) {
		this(email, nombre, apellidos, clave, null, fechaNacimiento);
	}

	/**
	 * Constructor vacío de la clase Usuario, requerido por JPA.
	 */
	protected Usuario() {
	}

	/**
	 * Recupera el identificador del usuario.
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Establece el identificador del usuario.
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Recupera el email del usuario.
	 * 
	 * @return Email del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Recupera el nombre del usuario.
	 * 
	 * @return Nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * 
	 * @param nombre Nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Recupera los apellidos del usuario.
	 * 
	 * @return Apellidos del usuario.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del usuario.
	 * 
	 * @param apellidos Apellidos del usuario.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Recupera la clave de acceso del usuario.
	 * 
	 * @return Clave de acceso del usuario.
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece la clave de acceso del usuario.
	 * 
	 * @param clave Clave de acceso del usuario.
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Recupera la fecha de nacimiento del usuario.
	 * 
	 * @return Fecha de nacimiento del usuario.
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del usuario.
	 * 
	 * @param fechaNacimiento Fecha de nacimiento del usuario.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Recupera el teléfono del usuario.
	 * 
	 * @return Teléfono del usuario.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono del usuario.
	 * 
	 * @param telefono Teléfono del usuario.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Recupera el estado de administrador del usuario.
	 * 
	 * @return true si el usuario es administrador, false en caso contrario.
	 */
	public boolean isAdministrador() {
		return administrador;
	}

	/**
	 * Establece si el usuario es administrador.
	 * 
	 * @param administrador true para que el usuario es administrador, false para
	 *                      que no lo sea.
	 */
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}
