package segundum.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import repositorio.Identificable;

/**
 * Clase que modela un producto de SegundUM.
 */
@Entity
public class Producto implements Identificable {
	
	/**
	 * Identificador único del producto.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * Recupera el identificador del producto.
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Establece el identificador del producto.
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
