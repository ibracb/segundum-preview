package umu.aadd.segundum.servicio;

import java.time.LocalDate;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Usuario;
import utils.StringUtilidades;

/**
 * Implementación del servicio de usuarios.
 */
public class ServicioUsuarios implements IServicioUsuarios {
	
	private Repositorio<Usuario, String> repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);
	
	@Override
	public String altaUsuario(String nombre, String apellidos, String email, String clave, String fechaNacimiento, String telefono) throws RepositorioException {
		if(!StringUtilidades.isDatoValido(nombre) || !StringUtilidades.isDatoValido(apellidos) || !StringUtilidades.isEmailValido(email)
				|| !StringUtilidades.isDatoValido(clave) || StringUtilidades.fechaParseada(fechaNacimiento) == null) {
			return null;
		}
		Usuario usuario;
		if(StringUtilidades.isTelefonoValido(telefono)) {
			usuario = new Usuario(email, nombre, apellidos, clave, telefono, LocalDate.parse(fechaNacimiento));
		}
		else {
			usuario = new Usuario(email, nombre, apellidos, clave, LocalDate.parse(fechaNacimiento));
		}
		return repositorioUsuarios.add(usuario);
	}

	@Override
	public void modificarUsuario(String idUsuario, String nombre, String apellidos, String clave, String fechaNacimiento, String telefono, Boolean administrador) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorioUsuarios.getById(idUsuario);
		if(usuario != null && StringUtilidades.isDatoValido(nombre)) {
			usuario.setNombre(nombre);
			repositorioUsuarios.update(usuario);
		}
		if(usuario != null && StringUtilidades.isDatoValido(apellidos)) {
			usuario.setApellidos(apellidos);
			repositorioUsuarios.update(usuario);
		}
		if(usuario != null && StringUtilidades.isDatoValido(clave)) {
			usuario.setClave(clave);
			repositorioUsuarios.update(usuario);
		}
		if(usuario != null && StringUtilidades.fechaParseada(fechaNacimiento) != null) {
			usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
			repositorioUsuarios.update(usuario);
		}
		if(usuario != null && StringUtilidades.isDatoValido(telefono)) {
			usuario.setTelefono(telefono);
			repositorioUsuarios.update(usuario);
		}
		if(usuario != null && administrador != null) {
			usuario.setAdministrador(administrador);
			repositorioUsuarios.update(usuario);
		}
	}

	@Override
	public Usuario recuperarUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		return repositorioUsuarios.getById(idUsuario);
	}
	
}
