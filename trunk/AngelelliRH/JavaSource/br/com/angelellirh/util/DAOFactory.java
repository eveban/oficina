package br.com.angelellirh.util;

import br.com.angelellirh.dao.JPAUtil;
import br.com.angelellirh.dao.ProfissionalDAO;

public class DAOFactory {

	public static ProfissionalDAO criarProfissionalDAO() {
		ProfissionalDAO profissionalDAO = new ProfissionalDAO();
		profissionalDAO.setEm(new JPAUtil().getEntityManager());
		return profissionalDAO;
	}
}
