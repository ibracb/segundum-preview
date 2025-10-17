package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de utilidad para gestionar el EntityManager en una aplicación mediante JPA.
 */
public class EntityManagerHelper {
	
	/**
	 * Fábrica de EntityManager para la aplicación.
	 */
	private static EntityManagerFactory entityManagerFactory;
	
	/**
	 * ThreadLocal para almacenar el EntityManager por hilo.
	 */
	private static final ThreadLocal<EntityManager> entityManagerHolder;
	
	/**
	 * Inicializa la fábrica de EntityManager y el ThreadLocal.
	 */
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("segundum");
		entityManagerHolder = new ThreadLocal<EntityManager>();
	}
	
	/**
	 * Obtiene el EntityManager asociado al hilo actual.
	 * Si no existe, crea uno nuevo y lo asocia al hilo.
	 * 
	 * @return El EntityManager asociado al hilo actual.
	 */
	public static EntityManager getEntityManager() {
		EntityManager entityManager = entityManagerHolder.get();
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = entityManagerFactory.createEntityManager();
			entityManagerHolder.set(entityManager);
		}
		return entityManager;
	}
	
	/**
	 * Cierra el EntityManager asociado al hilo actual y lo elimina del ThreadLocal.
	 */
	public static void closeEntityManager() {
		EntityManager entityManager = entityManagerHolder.get();
		if (entityManager != null) {
			entityManagerHolder.set(null);
			entityManager.close();
		}
	}	
}
