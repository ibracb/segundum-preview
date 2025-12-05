package umu.aadd.segundum.servicio.test;

import java.time.Month;
import java.util.List;

import javax.xml.bind.JAXBException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.modelo.Categoria;
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
	 * 
	 * @param args Argumentos de línea de comandos (no se usan).
	 * @throws RepositorioException si ocurre un error en el repositorio.
	 * @throws EntidadNoEncontrada  si no se encuentra una entidad.
	 * @throws JAXBException        si ocurre un error al procesar XML.
	 */
	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada, JAXBException {

		/**
		 * Prueba usuarios
		 */

		// Se obtiene servicio de usuarios con FactoriaServicios
		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);

		// Estos son los datos introducidos para dar de alta al usuario (sin teléfono)
		String email = "persona@um.es";
		String nombre = "Ibrahim";
		String apellidos = "Cherif Barry";
		String clave = "i.c.b";
		String fechaNacimiento = "2003-12-06";

		// COMO USUARIO, QUIERO REGISTRARME EN LA APLICACIÓN PARA PODER ACCEDER A SUS
		// FUNCIONALIDADES.
		// Vamos a dar de alta al usuario
		String idUsuario = servicioUsuarios.altaUsuario(nombre, apellidos, email, clave, fechaNacimiento, null);
		Usuario usuario = servicioUsuarios.recuperarUsuario(idUsuario);
		System.out.println("Alta usuario:");
		System.out.println(usuario.toString());

		// COMO USUARIO, QUIERO MODIFICAR MIS DATOS PERSONALES PARA MANTENER MI
		// INFORMACIÓN ACTUALIZADA.
		// Vamos a modificar los datos del usuario (le meteremos un teléfono además)
		servicioUsuarios.modificarUsuario(idUsuario, "Lucía", "Olmos Martínez", "l.o.m", "2004-01-01", "444444444",
				false);
		usuario = servicioUsuarios.recuperarUsuario(idUsuario);
		System.out.println("Modificación usuario:");
		System.out.println(usuario.toString());

		/**
		 * Prueba categorías
		 */

		// Se obtiene servicio de categorías con FactoriaServicios
		IServicioCategorias servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);

		// COMO ADMINISTRADOR, QUIERO CARGAR NUEVAS CATEGORÍAS PARA CLASIFICAR PRODUCTOS
		// Probamos a cargar una jerarquía de categorías a partir de un fichero XML
		// (Multimedia.xml)
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Multimedia.xml");

		// Tratamos de imprimir por consola información de todas las categorías raíz
		System.out.println("\n\nTODAS LAS CATEGORÍAS RAÍZ:\n");
		servicioCategorias.recuperarCategoriasRaiz().forEach(categoria -> {
			System.out.println(categoria.toString());
		});

		// Recuperamos la raíz con id=783 de Multimedia.xml
		Categoria categoriaRaizMultimedia = servicioCategorias.recuperarCategoria("783");

		if (categoriaRaizMultimedia != null) { // para cuando el usuario no sea administrador
			// Tratamos de imprimir por consola información de todas las categorías
			// descendientes de la raíz (id=783) en Multimedia.xml
			System.out.println("TODAS LAS CATEGORÍAS DESCENDIENTES DE LA RAÍZ DE MULTIMEDIA:\n");
			servicioCategorias.recuperarDescendientesCategoria(categoriaRaizMultimedia.getId()).forEach(categoria -> {
				System.out.println(categoria.toString());
			});

			// COMO ADMINISTRADOR, QUIERO MODIFICAR LAS CATEGORÍAS EXISTENTES PARA QUE
			// TENGAN UNA DECRIPCIÓN
			// Vamos a modificar la descripción de la categoría raíz de Multimedia.xml
			System.out.println("\nCategoría " + categoriaRaizMultimedia.getNombre() + " sin modificar descripción:");
			System.out.println(categoriaRaizMultimedia.toString());
			servicioCategorias.modificarCategoria(idUsuario, categoriaRaizMultimedia.getId(),
					"Productos multimedia a bajo coste");
			categoriaRaizMultimedia = servicioCategorias.recuperarCategoria(categoriaRaizMultimedia.getId());
			System.out.println("Categoría " + categoriaRaizMultimedia.getNombre() + " con descripción modificada:");
			System.out.println(categoriaRaizMultimedia.toString() + "\n\n");
		} else {
			System.out.println("No se ha cargado la categoría en el repositorio.");
		}

		servicioUsuarios.modificarUsuario(idUsuario, "Lucía", "Olmos Martínez", "l.o.m", "2004-01-01", "444444444",
				true);
		usuario = servicioUsuarios.recuperarUsuario(idUsuario);
		System.out.println("Modificación usuario:");
		System.out.println(usuario.toString());

		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Multimedia.xml");

		System.out.println("\n\nTODAS LAS CATEGORÍAS RAÍZ:\n");
		servicioCategorias.recuperarCategoriasRaiz().forEach(categoria -> {
			System.out.println(categoria.toString());
		});

		categoriaRaizMultimedia = servicioCategorias.recuperarCategoria("783");

		if (categoriaRaizMultimedia != null) { // para cuando el usuario no sea administrador
			// Tratamos de imprimir por consola información de todas las categorías
			// descendientes de la raíz (id=783) en Multimedia.xml
			System.out.println("TODAS LAS CATEGORÍAS DESCENDIENTES DE LA RAÍZ DE MULTIMEDIA:\n");
			servicioCategorias.recuperarDescendientesCategoria(categoriaRaizMultimedia.getId()).forEach(categoria -> {
				System.out.println(categoria.toString());
			});

			// COMO ADMINISTRADOR, QUIERO MODIFICAR LAS CATEGORÍAS EXISTENTES PARA QUE
			// TENGAN UNA DECRIPCIÓN
			// Vamos a modificar la descripción de la categoría raíz de Multimedia.xml
			System.out.println("\nCategoría " + categoriaRaizMultimedia.getNombre() + " sin modificar descripción:");
			System.out.println(categoriaRaizMultimedia.toString());
			servicioCategorias.modificarCategoria(idUsuario, categoriaRaizMultimedia.getId(),
					"Productos multimedia a bajo coste");
			categoriaRaizMultimedia = servicioCategorias.recuperarCategoria(categoriaRaizMultimedia.getId());
			System.out.println("Categoría " + categoriaRaizMultimedia.getNombre() + " con descripción modificada:");
			System.out.println(categoriaRaizMultimedia.toString() + "\n\n");
		} else {
			System.out.println("No se ha cargado la categoría en el repositorio.");
		}

		/**
		 * Prueba productos
		 */

		// Se obtiene servicio de categorías con FactoriaServicios
		IServicioProductos servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);

		// Probamos a cargar algunas jerarquías de categorías a partir de un fichero XML
		// (Multimedia.xml)
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Mobiliario.xml");

		// COMO USUARIO, QUIERO DAR DE ALTA UN PRODUCTO PARA PONERLO A LA VENTA
		// Probamos a crear un producto, con categoria "Sillas para salón comedor y
		// cocina", id: 5886
		String prodSillaId = servicioProductos.altaProducto("Silla", "Silla de madera resistente", "30.50",
				EstadoProducto.NUEVO, "5886", true, idUsuario);

		// Recuperar producto a través del ID
		Producto prodSilla = servicioProductos.recuperarProducto(prodSillaId);

		// Imprimimos sus propiedades
		System.out.println("\n\n" + prodSilla.toString());

		// Tratamos de modificar e imprimir las diferentes propiedades (añadir
		// visualización también)
		servicioProductos.anadirVisualizacion(prodSillaId);
		prodSilla = servicioProductos.recuperarProducto(prodSillaId);
		System.out.println(prodSilla.toString());

		// Creamos otros productos
		servicioCategorias.cargarJerarquiaCategorias(idUsuario, "categoriasXML/Equipamiento_deportivo.xml");
		String prodSillaId2 = servicioProductos.altaProducto("Tabla de surf", "Tabla de surf para niños", "100.0",
				EstadoProducto.ACEPTABLE, "3320", true, idUsuario);
		String prodSillaId3 = servicioProductos.altaProducto("Guantes", "Guantes de golf históricos", "100.0",
				EstadoProducto.COMO_NUEVO, "4466", true, idUsuario);

		// COMO USUARIO QUIERO ASOCIAR UN LUGAR DE RECOGIDA A UN PRODUCTO QUE HE PUESTO
		// A LA VENTA PARA FACILITAR SU ENTREGA
		// Vamos a asignar lugares de recogida a los dos últimos productos creados
		servicioProductos.asignarLugarRecogida(prodSillaId2, -40.53, 80.11, "Lugar bonito");
		servicioProductos.asignarLugarRecogida(prodSillaId3, 12.30, -50.64, "Lugar feo");

		// 3 visualizaciones para el producto con id prodSillaId2
		servicioProductos.anadirVisualizacion(prodSillaId2);
		servicioProductos.anadirVisualizacion(prodSillaId2);
		servicioProductos.anadirVisualizacion(prodSillaId2);

		// 2 visualizaciones para el producto con id prodSillaId3
		servicioProductos.anadirVisualizacion(prodSillaId3);
		servicioProductos.anadirVisualizacion(prodSillaId3);

		// COMO USUARIO, QUIERO MODIFICAR MIS PRODUCTOS A LA VENTA PARA CAMBIAR SU
		// PRECIO Y/O DESCRIPCIÓN PARA QUE SUS DATOS ESTÉN ACTUALIZADOS
		// Vamos a modificar los datos del producto con id prodSillaId2
		servicioProductos.modificarDatosProducto(prodSillaId2, "Tabla de surf para adultos", 153.82);

		// COMO USUARIO, QUIERO OBTENER UN RESUMEN MENSUAL DE MIS PRODUCTOS EN VENTA Y
		// SUS VISUALIZACIONES
		// Tratamos de recuperar el historial
		List<ProductoDTO> historial = servicioProductos.getHistorial(Month.NOVEMBER, 2025);
		System.out.println("\n\nHISTORIAL:");
		historial.forEach(productoDTO -> {
			System.out.println(productoDTO.toString());
		});
		System.out.println();

		// COMO USUARIO, QUIERO CONSULTAR LOS PRODUCTOS A LA VENTA FILTRANDO POR
		// DESCRIPCIÓN, CATEGORÍA, ESTADO Y PRECIO PARA LOCALIZAR PRODUCTOS QUE ME
		// INTERESAN
		// Recuperar los productos a la venta
		List<Producto> venta = servicioProductos.getProductosVenta("988", "", EstadoProducto.BUEN_ESTADO, 200.0);
		System.out.println("\n\nLos productos a la venta con categoria raíz"
				+ servicioCategorias.recuperarCategoria("988").getNombre() + " y mínimo en buen estado son: ");
		for (Producto p : venta) { // DEBE HABER DEVUELTO EL PRODUCTO GUANTE, QUE PERTENECE A LA SUBCATEGORÍA 4466
									// CON CATEGORÍA PADRE 988
			System.out.println(p.toString());
		}
	}

}
