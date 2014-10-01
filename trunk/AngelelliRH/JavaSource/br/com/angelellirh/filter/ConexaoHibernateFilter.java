package br.com.angelellirh.filter;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.angelellirh.dao.JPAUtil;

public class ConexaoHibernateFilter implements Filter {
	private EntityManager entityManager;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.entityManager = new JPAUtil().getEntityManager();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws ServletException {
		try {
			this.entityManager.getTransaction().begin();
			chain.doFilter(servletRequest, servletResponse);
			this.entityManager.getTransaction().commit();
			this.entityManager.close();

		} catch (Throwable ex) {
			try {
				if (this.entityManager.getTransaction().isActive()) {
					this.entityManager.getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException(ex);
		}

	}

}
