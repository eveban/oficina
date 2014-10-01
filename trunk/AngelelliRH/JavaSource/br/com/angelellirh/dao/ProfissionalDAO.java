package br.com.angelellirh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.angelellirh.model.Profissional;

public class ProfissionalDAO {
	private EntityManager em;

	public void salvar(Profissional profissional) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(profissional);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Profissional profissional) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(profissional);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Profissional profissional) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(profissional));
		em.getTransaction().commit();
		em.close();
	}

	public List<Profissional> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Profissional> query = em.getCriteriaBuilder()
				.createQuery(Profissional.class);
		query.select(query.from(Profissional.class));
		List<Profissional> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

	public Profissional carregar(Integer codigo) {
		this.em = new JPAUtil().getEntityManager();
		return em.find(Profissional.class, codigo);
	}

	public Profissional buscaPorLogin(String login) {
		this.em = new JPAUtil().getEntityManager();
		String jpql = "select p from Profissional p where p.login = :login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		return (Profissional) query.getSingleResult();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
