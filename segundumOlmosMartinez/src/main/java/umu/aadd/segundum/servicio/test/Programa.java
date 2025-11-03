package umu.aadd.segundum.servicio.test;

import javax.xml.bind.JAXBException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioUsuarios;

/**
 * Clase principal del programa de prueba de servicios.
 *
 */
public class Programa {

	/**
	 * Método principal que inicia la ejecución del programa.
	 * @param args Argumentos de línea de comandos (no se usan).
	 * @throws RepositorioException si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada si no se encuentra una entidad.
	 * @throws JAXBException si ocurre un error al procesar XML.
	 */
	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada, JAXBException {
		
		/**
		 * Prueba usuarios
		 */
		
		//Se obtiene servicio de usuarios con FactoriaServicios
		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
		
		//Estos son los datos introducidos para dar de alta al usuario (sin teléfono)
		String email = "persona@um.es";
		String nombre = "Ibrahim";
		String apellidos = "Cherif Barry";
		String clave = "i.c.b";
		String fechaNacimiento = "2003-12-06";
		
		//Vamos a dar de alta al usuario
		String idUsuario = servicioUsuarios.altaUsuario(nombre, apellidos, email, clave, fechaNacimiento, null);
		Usuario usuario = servicioUsuarios.recuperarUsuario(idUsuario);
		System.out.println("Usuario creado, con id " + idUsuario + ", y email " + email);
		System.out.println("Nombre: " + usuario.getNombre());
		System.out.println("Apellidos: " + usuario.getApellidos());
		System.out.println("Clave: " + usuario.getClave());
		System.out.println("Fecha de nacimiento: " + usuario.getFechaNacimiento().toString());
		System.out.println("Teléfono: " + usuario.getTelefono() + "\n\n");
		
		//Vamos a modificar los datos del usuario (le meteremos un teléfono además)
		servicioUsuarios.modificarUsuario(idUsuario, "Lucía", "Olmos Martínez", "l.o.m", "2004-01-01", "444444444");
		usuario = servicioUsuarios.recuperarUsuario(idUsuario);
		System.out.println("Usuario con id " + usuario.getId() + ", y email " + usuario.getEmail() + " actualizado!");
		System.out.println("Nombre: " + usuario.getNombre());
		System.out.println("Apellidos: " + usuario.getApellidos());
		System.out.println("Clave: " + usuario.getClave());
		System.out.println("Fecha de nacimiento: " + usuario.getFechaNacimiento().toString());
		System.out.println("Teléfono: " + usuario.getTelefono());
		
		
		
		/**
		 * Prueba categorías
		 */
		
		//Se obtiene servicio de categorías con FactoriaServicios
		IServicioCategorias servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
		
		//Probamos a cargar algunas jerarquías de categorías a partir de un fichero XML (Multimedia.xml)
		servicioCategorias.cargarJerarquiaCategorias("categoriasXML/Multimedia.xml");
		
		//Tratamos de imprimir por consola información de todas las categorías raíz
		System.out.println("\n\n\n\nTODAS LAS CATEGORÍAS RAÍZ:\n");
		servicioCategorias.recuperarCategoriasRaiz().forEach(categoria -> {
			System.out.println("Id: " + categoria.getId());
			System.out.println("Nombre: " + categoria.getNombre());
			System.out.println("Ruta: " + categoria.getRuta() + "\n\n");
		});
		
		//Tratamos de imprimir por consola información de todas las categorías descendientes de la raíz (id=783) en Multimedia.xml
		System.out.println("TODAS LAS CATEGORÍAS DESCENDIENTES DE LA RAÍZ DE MULTIMEDIA:\n");
		servicioCategorias.recuperarDescendientesCategoria("783").forEach(categoria -> {
			System.out.println("Id: " + categoria.getId());
			System.out.println("Nombre: " + categoria.getNombre());
			System.out.println("Ruta: " + categoria.getRuta() + "\n\n");
		});
		
		
	}

}
