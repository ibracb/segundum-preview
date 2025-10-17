package segundum.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import repositorio.Identificable;

/**
 * Clase que modela un usuario de SegundUM.
 */
@Entity
public class Usuario implements Identificable {
	
	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	/**
	 * Recupera el identificador del usuario.
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Establece el identificador del usuario.
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
