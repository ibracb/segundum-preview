package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import umu.aadd.segundum.dto.UsuarioDTO;
import utils.StringUtilidades;

/**
 * Bean de sesión que mantiene los datos del usuario autenticado durante toda la sesión.
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class SesionUsuario implements Serializable {
	
	/**
	 * DTO del usuario autenticado en la sesión.
	 */
	private UsuarioDTO usuarioDTO;
	
	/**
	 * Constructor vacío para el bean de sesión del usuario.
	 */
	public SesionUsuario() {
	}
	
	/**
	 * Establece el usuario en la sesión.
	 * 
	 * @param usuarioDTO DTO del usuario autenticado.
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	
	/**
	 * Recupera el usuario de la sesión.
	 * 
	 * @return DTO del usuario autenticado.
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	
	/**
	 * Verifica si hay un usuario autenticado en la sesión.
	 * 
	 * @return true si hay usuario autenticado, false en caso contrario.
	 */
	public boolean isAutenticado() {
		return usuarioDTO != null;
	}
	
	/**
	 * Cierra la sesión del usuario.
	 */
	public void cerrarSesion() {
			this.usuarioDTO = null;
	}
	
	/**
	 * Recupera el nombre completo del usuario.
	 * 
	 * @return Nombre completo o cadena vacía si no hay usuario.
	 */
	public String getNombreCompleto() {
		if (usuarioDTO != null) {
			return usuarioDTO.getNombre() + StringUtilidades.ESPACIO_EN_BLANCO + usuarioDTO.getApellidos();
		}
		return StringUtilidades.CADENA_VACIA;
	}
}