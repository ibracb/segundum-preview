package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.servicio.IServicioUsuarios;
import utils.StringUtilidades;

/**
 * Bean de gestión del login de usuarios.
 */
@SuppressWarnings("serial")
@Named("login")
@ViewScoped
public class Login implements Serializable {
	
	/**
	 * Campo email del usuario.
	 */
	private String email;
	
	/**
	 * Campo clave del usuario.
	 */
	private String clave;
	
	/**
	 * Servicio de usuarios.
	 */
	private IServicioUsuarios servicioUsuarios;
	
	/**
	 * Indica si ha habido un error en el login.
	 */
	private boolean error;
	
	/**
	 * DTO del usuario autenticado.
	 */
	private UsuarioDTO usuarioDTO;
	
	/**
	 * Constructor del bean de login.
	 */
	public Login() {
		this.servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
	}
	
	/**
	 * Método para loguear al usuario.
	 */
	public void loguear() {
		if(!StringUtilidades.isEmailValido(email) || !StringUtilidades.isDatoValido(clave)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", 
		                "Email o clave no válidos. Email: " + email + ", Clave vacía: " + StringUtilidades.isDatoValido(clave)));
			return;
		}
		try {
			Usuario usuario = servicioUsuarios.recuperarUsuario(email, clave);
			usuarioDTO = servicioUsuarios.recuperarUsuarioDTO(usuario.getId());
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect("principal.xhtml");
			error = false;
		}
		catch(Exception e) {
			error = true;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "USUARIO NO AUTENTICADO", e.getMessage()));
		}
	}

	/**
	 * Recupera el email del usuario.
	 * @return email del usuario.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email del usuario.
	 * @param email email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Recupera la clave del usuario.
	 * @return clave del usuario.
	 */
	public String getClave() {
		return clave;
	}
	
	/**
	 * Establece la clave del usuario.
	 * @param clave clave del usuario.
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Indica si ha habido un error en el login.
	 * @return true si ha habido un error, false en caso contrario.
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Recupera el DTO del usuario autenticado.
	 * @return DTO del usuario autenticado.
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	
}
