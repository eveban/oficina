package br.com.officinasp.filter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

		Object currentUser = session.getAttribute("currentUser");

		/*
		 * System.out.println(currentPage); System.out.println(isLoginPage);
		 * System.out.println("Current user: " + currentUser);
		 */

		// if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {

		if (!isLoginPage && currentUser == null) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
		}
		// }
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		// return PhaseId.RESTORE_VIEW;
		return PhaseId.RENDER_RESPONSE;
	}

}
