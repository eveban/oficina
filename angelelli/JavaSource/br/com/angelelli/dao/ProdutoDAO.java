package br.com.angelelli.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.angelelli.modelo.Produto;

public class ProdutoDAO {

	public List<Produto> listaProdutoOrdem() {
		EntityManager em = new JPAUtil().getEntityManager();
		//em.getTransaction().begin();
		String jpql = "select p from Produto p order by p.nome";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

}
