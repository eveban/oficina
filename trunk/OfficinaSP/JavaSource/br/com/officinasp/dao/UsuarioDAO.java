package br.com.officinasp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.officinasp.modelo.Usuario;

public class UsuarioDAO {
	private EntityManager em;

	public void salvar(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(usuario));
		em.getTransaction().commit();
		em.close();
	}

	public List<Usuario> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(
				Usuario.class);
		query.select(query.from(Usuario.class));
		List<Usuario> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

	public Usuario carregar(Integer codigo) {
		this.em = new JPAUtil().getEntityManager();
		return em.find(Usuario.class, codigo);
	}

	public Usuario buscaPorLogin(String cpf) {
		this.em = new JPAUtil().getEntityManager();
		String jpql = "select u from Usuario u where u.cpf = :cpf";
		Query query = em.createQuery(jpql);
		query.setParameter("cpf", cpf);
		return (Usuario) query.getSingleResult();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
