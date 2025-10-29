package repositorio;

import utils.PropertiesReader;

/**
 * Factoría que encapsula la implementación del repositorio.
 * 
 * Utiliza un fichero de propiedades para cargar la implementación del
 * repositorio.
 * 
 */
public class FactoriaRepositorios {

	/**
	 * Fichero de propiedades con las implementaciones de los repositorios.
	 */
	private static final String PROPERTIES = "repositorios.properties";

	/**
	 * Recupera el repositorio para una entidad dada.
	 * 
	 * @param <T>     Tipo de la entidad.
	 * @param <K>     Tipo del identificador de la entidad.
	 * @param <R>     Tipo del repositorio.
	 * 
	 * @param entidad Clase de la entidad.
	 * 
	 * @return Repositorio para la entidad dada.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K, R extends Repositorio<T, K>> R getRepositorio(Class<?> entidad) {
		try {
			PropertiesReader properties = new PropertiesReader(PROPERTIES);
			String clase = properties.getProperty(entidad.getName());
			return (R) Class.forName(clase).getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No se ha podido obtener el repositorio para la entidad: " + entidad.getName());
		}
	}

}
