package br.com.officinasp.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.officinasp.controlador.ContextoBean;

public class ContextoUtil {

	public static ContextoBean getContextoBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ContextoBean contextoBean = (ContextoBean) session
				.getAttribute("contextoBean");
		return contextoBean;

	}

}
