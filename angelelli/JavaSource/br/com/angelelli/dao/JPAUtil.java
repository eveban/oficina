package br.com.angelelli.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("angelelli");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
