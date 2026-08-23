package umu.aadd.segundum.web;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.servicio.IServicioUsuarios;
import utils.StringUtilidades;

/**
 * Bean de gestión del registro de usuarios.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorRegistro implements Serializable {

	/**
	 * Campo nombre del usuario.
	 */
	private String nombre;

	/**
	 * Campo apellidos del usuario.
	 */
	private String apellidos;

	/**
	 * Campo email del usuario.
	 */
	private String email;

	/**
	 * Campo clave del usuario.
	 */
	private String clave;

	/**
	 * Campo fecha de nacimiento del usuario.
	 */
	private LocalDate fechaNacimiento;

	/**
	 * Campo teléfono del usuario.
	 */
	private String telefono;

	/**
	 * Servicio de usuarios.
	 */
	private IServicioUsuarios servicioUsuarios;

	/**
	 * Indica si ha habido un error en el registro.
	 */
	private boolean error;
	
	/**
	 * Bean de sesión del usuario autenticado.
	 */
	@Inject
	private SesionUsuario sesionUsuario;

	/**
	 * Constructor del bean controlador de registro.
	 */
	public ControladorRegistro() {
		this.servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
	}

	/**
	 * Método para registrar un nuevo usuario.
	 */
	public void registrar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!StringUtilidades.isDatoValido(nombre) || !StringUtilidades.isDatoValido(apellidos)
				|| !StringUtilidades.isDatoValido(clave)) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Debe rellenar todos los datos"));
			return;
		}
		if (!StringUtilidades.isEmailValido(email)) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Email inválido"));
			return;
		}
		LocalDate fecha;
		if ((fecha = StringUtilidades.fechaParseada(fechaNacimiento.toString())) == null || !(StringUtilidades.fechaValida(fecha))) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Fecha de nacimiento inválida"));
			return;
		}
		if (!StringUtilidades.isTelefonoValido(telefono)) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "Teléfono inválido"));
			return;
		}
		try {
			UsuarioDTO usuarioDTO = servicioUsuarios.altaUsuario(nombre, apellidos, email, clave, fechaNacimiento.toString(), telefono);
			sesionUsuario.setUsuarioDTO(usuarioDTO);
			facesContext.getExternalContext().redirect("principal.xhtml");
			error = false;
		} catch (Exception e) {
			error = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "USUARIO NO PUDO SER REGISTRADO", e.getMessage()));
		}
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
	 * Recupera el email del usuario.
	 * 
	 * @return Email del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email del usuario.
	 * 
	 * @param email Email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Recupera la clave del usuario.
	 * 
	 * @return Clave del usuario.
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece la clave del usuario.
	 * 
	 * @param clave Clave del usuario.
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
	 * Indica si ha habido un error en el registro.
	 * 
	 * @return true si ha habido un error, false en caso contrario.
	 */
	public boolean isError() {
		return error;
	}

}
