package br.com.angelellirh.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AtualizaBD {
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("angelellirh");

	}

}
