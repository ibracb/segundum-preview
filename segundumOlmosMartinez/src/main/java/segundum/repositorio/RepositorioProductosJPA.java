package segundum.repositorio;

import repositorio.RepositorioJPA;
import segundum.modelo.Producto;

/**
 * Repositorio JPA para la entidad Producto.
 */
public class RepositorioProductosJPA extends RepositorioJPA<Producto> {

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Devuelve la clase de la entidad Producto.
	 * </p>
	 */
	@Override
	public Class<Producto> getClase() {
		// TODO Auto-generated method stub
		return null;
	}

}
