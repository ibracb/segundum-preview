package umu.aadd.segundum.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import repositorio.Identificable;

@Entity
public class Categoria implements Identificable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * Recupera el identificador de la categoría.
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Establece el identificador de la categoría.
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
