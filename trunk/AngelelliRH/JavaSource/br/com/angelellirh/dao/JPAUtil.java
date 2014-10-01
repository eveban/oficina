package br.com.angelellirh.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("angelellirh");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
