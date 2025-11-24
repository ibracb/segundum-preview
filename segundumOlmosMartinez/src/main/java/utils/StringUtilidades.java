package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Clase con utilidades para el manejo de Strings.
 */
public class StringUtilidades {
	
	/**
	 * Constante de espacio en blanco.
	 */
	public static final String ESPACIO_EN_BLANCO = " ";
	
	/**
	 * Expresión regular para validar un número de teléfono de nueve dígitos
	 */
	private static final String NUEVE_DIGITOS = "\\d{9}";

	/**
	 * Expresión regular para validar una dirección de correo electrónico.
	 */
	private static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";

	/**
	 * Constructor privado para evitar la creación de instancias de la clase
	 * StringUtilidades.
	 */
	private StringUtilidades() {
	}

	/**
	 * Comprueba si un dato es válido (no nulo y no vacío).
	 * 
	 * @param dato El dato a comprobar.
	 * @return true si el dato es válido, false en caso contrario.
	 */
	public static boolean isDatoValido(String dato) {
		return dato != null && !dato.trim().isBlank();
	}

	/**
	 * Parsea una cadena de texto a un objeto LocalDate.
	 * 
	 * @param fecha La cadena de texto que representa la fecha.
	 * @return El objeto LocalDate si el parseo es exitoso, null en caso contrario.
	 */
	public static LocalDate fechaParseada(String fecha) {
		try {
			LocalDate fechaParseada;
			fechaParseada = LocalDate.parse(fecha);
			return fechaParseada;
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * Comprueba si un número de teléfono es válido (nueve dígitos).
	 * 
	 * @param telefono El número de teléfono a comprobar.
	 * @return true si el número de teléfono es válido, false en caso contrario.
	 */
	public static boolean isTelefonoValido(String telefono) {
		if (telefono == null) {
			return false;
		}
		return telefono.matches(NUEVE_DIGITOS);
	}
	
	/**
	 * Comprueba si una dirección de correo electrónico es válida.
	 * 
	 * @param email La dirección de correo electrónico a comprobar.
	 * @return true si la dirección de correo electrónico es válida, false en caso
	 *         contrario.
	 */
	public static boolean isEmailValido(String email) {
		if (email == null) {
			return false;
		}
		return email.matches(EMAIL);
	}

}
