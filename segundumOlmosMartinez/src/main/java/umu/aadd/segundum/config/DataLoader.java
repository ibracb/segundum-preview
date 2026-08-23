package umu.aadd.segundum.config;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.UsuarioDTO;
import umu.aadd.segundum.modelo.Usuario;
import umu.aadd.segundum.servicio.IServicioCategorias;
import umu.aadd.segundum.servicio.IServicioUsuarios;

/**
 * Listener para cargar datos iniciales al arrancar la aplicación.
 */
@WebListener
public class DataLoader implements ServletContextListener {
	
	/**
	 * Servicio de usuarios.
	 */
	private IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
	
	/**
	 * Servicio de categorías.
	 */
	private IServicioCategorias servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Inicializando datos...");
		System.out.println("Creando el usuario administrador...");
		try {
			UsuarioDTO adminDTO = servicioUsuarios.altaUsuario("admin","admin","admin@segundum.es","admin",LocalDate.of(2025, 11, 12).toString(),"123456789");
			String u = adminDTO.getId();
			Usuario admin = servicioUsuarios.recuperarUsuario(u);
			servicioUsuarios.modificarUsuario(admin.getId(), admin.getNombre(), admin.getApellidos(), admin.getClave(),
					admin.getFechaNacimiento().toString(), admin.getTelefono(), true);			
			try {
				System.out.println("Cargando categorías iniciales al arrancar...");
	
				String rutaBase = getClass().getResource("/categoriasXML").getPath();
				File carpeta = new File(rutaBase);
				if (carpeta.exists() && carpeta.isDirectory()) {
					File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".xml"));
					if (archivos != null) {
						for (File xml : archivos) {
							try {
								servicioCategorias.cargarJerarquiaCategorias(admin.getId(), xml.getAbsolutePath());
								System.out.println("Cargada categoría: " + xml.getName());
							}
							catch (Exception e) {
								System.err.println("Error cargando " + xml.getName() + ": " + e.getMessage());
							}
						}
					}
				}
			}
			catch (Exception e) {
				System.err.println("Error cargando categorías iniciales: " + e.getMessage());
			}
		}
		catch (Exception e) {
			System.err.println("Error creando admin por defecto: " + e.getMessage());
		}		
	}

}
