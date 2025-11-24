package umu.aadd.segundum.servicio;

import java.time.LocalDate;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.repositorio.RepositorioUsuariosAdHoc;
import utils.StringUtilidades;

/**
 * Implementación del servicio de usuarios.
 */
public class ServicioUsuarios implements IServicioUsuarios {

	private RepositorioUsuariosAdHoc repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public String altaUsuario(String nombre, String apellidos, String email, String clave, String fechaNacimiento,
			String telefono) throws RepositorioException {
		if (!StringUtilidades.isDatoValido(nombre) || !StringUtilidades.isDatoValido(apellidos)
				|| !StringUtilidades.isEmailValido(email) || !StringUtilidades.isDatoValido(clave)
				|| StringUtilidades.fechaParseada(fechaNacimiento) == null) {
			return null;
		}
		Usuario usuario;
		if (StringUtilidades.isTelefonoValido(telefono)) {
			usuario = new Usuario(email, nombre, apellidos, clave, telefono, LocalDate.parse(fechaNacimiento));
		} else {
			usuario = new Usuario(email, nombre, apellidos, clave, LocalDate.parse(fechaNacimiento));
		}
		return repositorioUsuarios.add(usuario);
	}

	@Override
	public void modificarUsuario(String idUsuario, String nombre, String apellidos, String clave,
			String fechaNacimiento, String telefono, Boolean administrador)
			throws RepositorioException, EntidadNoEncontrada {
		Usuario usuario = repositorioUsuarios.getById(idUsuario);
		if (usuario != null && StringUtilidades.isDatoValido(nombre)) {
			usuario.setNombre(nombre);
			repositorioUsuarios.update(usuario);
		}
		if (usuario != null && StringUtilidades.isDatoValido(apellidos)) {
			usuario.setApellidos(apellidos);
			repositorioUsuarios.update(usuario);
		}
		if (usuario != null && StringUtilidades.isDatoValido(clave)) {
			usuario.setClave(clave);
			repositorioUsuarios.update(usuario);
		}
		if (usuario != null && StringUtilidades.fechaParseada(fechaNacimiento) != null) {
			usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
			repositorioUsuarios.update(usuario);
		}
		if (usuario != null && StringUtilidades.isDatoValido(telefono)) {
			usuario.setTelefono(telefono);
			repositorioUsuarios.update(usuario);
		}
		if (usuario != null && administrador != null) {
			usuario.setAdministrador(administrador);
			repositorioUsuarios.update(usuario);
		}
	}

	@Override
	public Usuario recuperarUsuario(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		if (repositorioUsuarios.getIds().stream().anyMatch(id -> id.equals(idUsuario))) {
			Usuario usuario = repositorioUsuarios.getById(idUsuario);
			if (usuario == null) {
				System.err.println("No se puede recuperar el usuario con id " + idUsuario
						+ " porque no se encuentra en el repositorio");
				return null;
			}
			return usuario;
		}
		return null;
	}

	@Override
	public Usuario recuperarUsuario(String email, String clave) throws RepositorioException {
		return repositorioUsuarios.getByEmailAndClave(email, clave);
	}
	
	@Override
	public UsuarioDTO recuperarUsuarioDTO(String idUsuario) throws RepositorioException, EntidadNoEncontrada {
		if (idUsuario == null || idUsuario.isEmpty()) {
			throw new IllegalArgumentException("idUsuario: no debe ser nulo ni vacio");
		}
		return convertirEnDTO(repositorioUsuarios.getById(idUsuario));
	}
	
	/**
	 * Convierte un usuario en su representación DTO.
	 * 
	 * @param usuario Usuario a convertir.
	 * @return UsuarioDTO correspondiente al usuario especificado.
	 */
	private UsuarioDTO convertirEnDTO(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getEmail(), usuario.getNombre(), usuario.getApellidos(),
				usuario.getFechaNacimiento(), usuario.getTelefono(), usuario.isAdministrador());
	}

}
