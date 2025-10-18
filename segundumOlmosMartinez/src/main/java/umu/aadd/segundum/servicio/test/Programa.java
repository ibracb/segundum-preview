package umu.aadd.segundum.servicio.test;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.servicio.IServicioUsuarios;

/**
 * Clase principal del programa de prueba de servicios.
 *
 */
public class Programa {

	/**
	 * Método principal que inicia la ejecución del programa.
	 * @param args Argumentos de línea de comandos (no se usan).
	 */
	public static void main(String[] args) {
		
		//Se obtiene servicio de usuarios con FactoriaServicios
		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
		
		//Estos son los datos introducidos para dar de alta al usuario (sin teléfono)
		String email = "persona@um.es";
		String nombre = "Ibrahim";
		String apellidos = "Cherif Barry";
		String clave = "i.c.b";
		String fechaNacimiento = "2003-12-06";
		
		//Vamos a dar de alta al usuario
		String idUsuario = null;
		try {
			idUsuario = servicioUsuarios.altaUsuario(nombre, apellidos, email, clave, fechaNacimiento, null);
			Usuario usuario = servicioUsuarios.recuperarUsuario(idUsuario);
			System.out.println("Usuario creado, con id " + idUsuario + ", y email " + email);
			System.out.println("Nombre: " + usuario.getNombre());
			System.out.println("Apellidos: " + usuario.getApellidos());
			System.out.println("Clave: " + usuario.getClave());
			System.out.println("Fecha de nacimiento: " + usuario.getFechaNacimiento().toString());
			System.out.println("Teléfono: " + usuario.getTelefono() + "\n\n");
		}
		catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
		
		//Vamos a modificar los datos del usuario (le meteremos un teléfono además)
		try {
			servicioUsuarios.modificarUsuario(idUsuario, "Lucía", "Olmos Martínez", "l.o.m", "2004-01-01", "444444444");
			Usuario usuario = servicioUsuarios.recuperarUsuario(idUsuario);
			System.out.println("Usuario con id " + usuario.getId() + ", y email " + usuario.getEmail() + " actualizado!");
			System.out.println("Nombre: " + usuario.getNombre());
			System.out.println("Apellidos: " + usuario.getApellidos());
			System.out.println("Clave: " + usuario.getClave());
			System.out.println("Fecha de nacimiento: " + usuario.getFechaNacimiento().toString());
			System.out.println("Teléfono: " + usuario.getTelefono());
		}
		catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
		
	}

}
