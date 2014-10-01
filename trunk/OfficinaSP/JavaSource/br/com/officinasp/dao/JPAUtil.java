package br.com.officinasp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("oficinaSP");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
