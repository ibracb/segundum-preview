package umu.aadd.segundum.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean de gestión de la página principal.
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorPrincipal implements Serializable {
	
	/**
	 * Bean de sesión del usuario autenticado.
	 */
	@Inject
	private SesionUsuario sesionUsuario;
	
	/**
	 * Constructor del bean controlador principal.
	 */
	public ControladorPrincipal() {
		
	}
	
	/**
	 * Método para cerrar la sesión del usuario.
	 */
	public void logout() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().invalidateSession();
			sesionUsuario.cerrarSesion();
			facesContext.getExternalContext().redirect("inicio.xhtml");
		}
		catch (IOException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL CERRAR SESIÓN", e.getMessage()));
		}
	}

}
