package br.com.officinasp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.officinasp.modelo.Cidade;

public class CidadeDAO {

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidadesByEstado(int id) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "select c from " + Cidade.class.getName()
				+ " as c where c.estado.id like :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidadesByEstadoTeste(int id) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "select c from " + Cidade.class.getName()
				+ " as c where c.estado.id like :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
