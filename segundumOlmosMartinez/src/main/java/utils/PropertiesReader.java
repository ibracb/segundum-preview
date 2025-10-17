package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase de utilidad para leer ficheros de propiedades.
 */
public class PropertiesReader {
	
	/**
	 * Propiedades cargadas desde el fichero.
	 */
	private Properties properties;
	
	/**
	 * Constructor que carga las propiedades desde el fichero especificado.
	 * @param propertyFileName nombre del fichero de propiedades.
	 * @throws IOException si ocurre un error al leer el fichero.
	 */
    public PropertiesReader(String propertyFileName) throws IOException {
    	InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        this.properties.load(is);
    }
    
    /**
	 * Obtiene el valor de una propiedad por su nombre.
	 * @param propertyName Nombre de la propiedad.
	 * @return Valor de la propiedad.
	 */
    public String getProperty(String propertyName) {
    	return this.properties.getProperty(propertyName);
    }
    
}