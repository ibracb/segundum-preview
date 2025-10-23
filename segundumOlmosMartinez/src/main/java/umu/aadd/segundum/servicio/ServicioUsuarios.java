package umu.aadd.segundum.servicio;

import java.time.LocalDate;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.Usuario;
import utils.StringUtilidades;

/**
 * Implementación del servicio de usuarios.
 */
public class ServicioUsuarios implements IServicioUsuarios {
	
	private Repositorio<Usuario, String> repoUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);
	
	@Override
	public String altaUsuario(String nombre, String apellidos, String email, String clave, String fechaNacimiento, String telefono) throws RepositorioException {
		if(StringUtilidades.fechaParseada(fechaNacimiento) == null) {
			return null;
		}
		Usuario usuario;
		if(StringUtilidades.isTelefonoValido(telefono)) {
			usuario = new Usuario(email, nombre, apellidos, clave, telefono, LocalDate.parse(fechaNacimiento));
		}
		else {
			usuario = new Usuario(email, nombre, apellidos, clave, LocalDate.parse(fechaNacimiento));
		}
		return repoUsuarios.add(usuario);
	}

	@Override
	public void modificarUsuario(String idUsuario, String nombre, String apellidos, String clave, String fechaNacimiento, String telefono) throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repoUsuarios.getById(idUsuario);
		if(usuario != null && StringUtilidades.isDatoValido(nombre)) {
			usuario.setNombre(nombre);
		}
		if(usuario != null && StringUtilidades.isDatoValido(apellidos)) {
			usuario.setApellidos(apellidos);
		}
		if(usuario != null && StringUtilidades.isDatoValido(clave)) {
			usuario.setClave(clave);
		}
		if(usuario != null && StringUtilidades.fechaParseada(fechaNacimiento) != null) {
			usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
		}
		if(usuario != null && StringUtilidades.isDatoValido(telefono)) {
			usuario.setTelefono(telefono);
		}
		repoUsuarios.update(usuario);
	}

	@Override
	public Usuario recuperarUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		return repoUsuarios.getById(idUsuario);
	}
	
	@Override
	public Usuario getById(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		return repoUsuarios.getById(idUsuario);
	}
	
	
}
