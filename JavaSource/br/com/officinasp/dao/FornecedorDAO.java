package br.com.officinasp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.officinasp.modelo.Fornecedor;

public class FornecedorDAO {

	public void salvar(Fornecedor fornecedor) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(fornecedor);
		em.getTransaction().commit();
		em.close();
	}

	public List<Fornecedor> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Fornecedor> query = em.getCriteriaBuilder().createQuery(
				Fornecedor.class);
		query.select(query.from(Fornecedor.class));
		List<Fornecedor> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

}
