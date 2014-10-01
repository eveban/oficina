package br.com.angelelli.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaEntityManagerFactoryParaAtualizarBanco {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("angelelli");
	}
	
}
