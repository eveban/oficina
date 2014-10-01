package br.com.officinasp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.officinasp.modelo.Empresa;

public class EmpresaDAO {
	private EntityManager em;

	public void salvar(Empresa empresa) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		em.close();
	}

	public void atualizar(Empresa empresa) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(empresa);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Empresa empresa) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(empresa));
		em.getTransaction().commit();
		em.close();
	}

	public List<Empresa> listar() {
		em = new JPAUtil().getEntityManager();
		String jpql = "select e from Empresa e order by e.razaoSocial";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
