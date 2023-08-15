package Controll.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;
	private static final String PERSISTENCE_UNIT_NAME = "nhutlq";

	private static synchronized EntityManagerFactory getEntityManagerFactory() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	public static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	public static void shutDown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
