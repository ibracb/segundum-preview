package umu.aadd.segundum.servicio.test;

import java.time.Month;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioProductos;
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
		servicioUsuarios.modificarUsuario(idUsuario, "Lucía", "Olmos Martínez", "l.o.m", "2004-01-01", "444444444", true);
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
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Multimedia.xml");
		
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
		
		
		/**
		 * Prueba productos
		 */
		
		//Se obtiene servicio de categorías con FactoriaServicios
		IServicioProductos servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
		
		
		//Probamos a cargar algunas jerarquías de categorías a partir de un fichero XML (Multimedia.xml)
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Mobiliario.xml");
		
		//Probamos a crear un producto, con categoria "Sillas para salón comedor y cocina", id: 5886
		String prodSillaId = servicioProductos.altaProducto("Silla", "Silla de madera resistente", 30.50, EstadoProducto.NUEVO, "5886", true, idUsuario);
		
		
		//Recuperar producto a través del ID
		Producto prodSilla = servicioProductos.recuperarProducto(prodSillaId);
		
		//Imprimimos sus propiedades
		System.out.println(prodSilla.toString());
		
		//Tratamos de modificar e imprimir las diferentes propiedades (añadir visualización también)
		servicioProductos.anadirVisualizacion(prodSillaId);
		System.out.println(prodSilla.toString());
		
		//Creamos otros productos
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Equipamiento_deportivo.xml");
		String prodSillaId2 = servicioProductos.altaProducto("Tabla de surf", "Tabla de surf para niños", 100.0, EstadoProducto.BUEN_ESTADO, "3320", true, idUsuario);
		String prodSillaId3 = servicioProductos.altaProducto("Guantes", "Guantes de golf históricos", 100.0, EstadoProducto.COMO_NUEVO, "4466", true, idUsuario);
		
		//Vamos a asignar lugares de recogida a los dos últimos productos creados
		servicioProductos.asignarLugarRecogida(prodSillaId2, -40.53, 80.11, "Lugar bonito");
		servicioProductos.asignarLugarRecogida(prodSillaId3, 12.30, -50.64, "Lugar feo");
		
		//Vamos a modificar los datos del producto con id prodSillaId2
		servicioProductos.modificarDatosProducto(prodSillaId2, "Tabla de surf para adultos", 153.82);
		
		//Tratamos de recuperar el historial
		Map<Producto,String> historial = servicioProductos.getHistorial(Month.NOVEMBER, 2025);
		for(Producto p : historial.keySet()) {
			System.out.println(historial.get(p));
		}
		
		//Recuperar los productos a la venta
		List<Producto> venta = servicioProductos.getProductosVenta("988", "", EstadoProducto.COMO_NUEVO, 200.0);
		System.out.println("Los productos a la venta con categoria raíz" + servicioCategorias.recuperarCategoria("988").getNombre() + " y estado como nuevo son: ");
		for(Producto p : venta) { //DEBE HABER DEVUELTO EL PRODUCTO GUANTE, QUE PERTENECE A LA SUBCATEGORÍA 4466 CON CATEGORÍA PADRE 988
			System.out.println(p.toString());
		}
	}

}
