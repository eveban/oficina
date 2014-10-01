package br.com.angelellirh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.angelellirh.model.Cidade;

public class CidadeDAO {
	private final EntityManager em;
	private final DAO<Cidade> dao;

	public CidadeDAO(EntityManager em) {
		this.em = em;
		dao = new DAO<Cidade>(Cidade.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getCidadesByEstado(Long id) {
		String jpql = "select c from " + Cidade.class.getName()
				+ " as c where c.estado.id like :id";
		Query query = this.em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	public DAO<Cidade> getDao() {
		return dao;
	}
}
