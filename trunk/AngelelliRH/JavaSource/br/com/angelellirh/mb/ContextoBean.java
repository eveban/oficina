package br.com.angelellirh.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.angelellirh.dao.ProfissionalDAO;
import br.com.angelellirh.model.Profissional;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {
	private Profissional profissionalLogado = null;
	//private Conta contaAtiva = null;
	//private Locale localizacao = null;

	// private List<Locale> idiomas;

	public Profissional getProfissionalLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if (this.profissionalLogado == null
				|| !login.equals(this.profissionalLogado.getLogin())) {
			if (login != null) {

				ProfissionalDAO profissionalDAO = new ProfissionalDAO();
				this.profissionalLogado = profissionalDAO.buscaPorLogin(login);
				// UsuarioRN usuarioRN = new UsuarioRN();
				// this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				// this.contaAtiva = null;
			}
		}
		return profissionalLogado;
	}

	public void setProfissionalLogado(Profissional profissionalLogado) {
		this.profissionalLogado = profissionalLogado;
	}

	/*
	 * public Conta getContaAtiva() { if (this.contaAtiva == null) { Usuario
	 * usuario = this.getUsuarioLogado(); ContaRN contaRN = new ContaRN();
	 * this.contaAtiva = contaRN.buscarFavorita(usuario); if (this.contaAtiva ==
	 * null) { List<Conta> contas = contaRN.listar(usuario); if (contas != null)
	 * { for (Conta conta : contas) { this.contaAtiva = conta; break; } } } }
	 * return contaAtiva; }
	 * 
	 * public void setContaAtiva(ValueChangeEvent event) { Integer conta =
	 * (Integer) event.getNewValue(); ContaRN contaRN = new ContaRN();
	 * this.contaAtiva = contaRN.carregar(conta); }
	 * 
	 * public Locale getLocaleUsuario() { if (this.localizacao == null) {
	 * Usuario usuario = this.getUsuarioLogado(); String idioma =
	 * usuario.getIdioma(); String[] info = idioma.split("_"); this.localizacao
	 * = new Locale(info[0], info[1]); } return this.localizacao; }
	 * 
	 * public Locale getLocalizacao() { return localizacao; }
	 * 
	 * public void setLocalizacao(Locale localizacao) { this.localizacao =
	 * localizacao; }
	 * 
	 * public List<Locale> getIdiomas() { FacesContext context =
	 * FacesContext.getCurrentInstance(); Iterator<Locale> locales =
	 * context.getApplication() .getSupportedLocales(); this.idiomas = new
	 * ArrayList<Locale>(); while (locales.hasNext()) {
	 * this.idiomas.add(locales.next()); } return idiomas; }
	 * 
	 * public void setIdiomas(List<Locale> idiomas) { this.idiomas = idiomas; }
	 * 
	 * public void setIdiomaUsuario(String idioma) { UsuarioRN usuarioRN = new
	 * UsuarioRN(); this.usuarioLogado =
	 * usuarioRN.carregar(this.getUsuarioLogado() .getCodigo());
	 * this.usuarioLogado.setIdioma(idioma);
	 * usuarioRN.salvar(this.usuarioLogado); String[] info = idioma.split("_");
	 * Locale locale = new Locale(info[0], info[1]);
	 * 
	 * FacesContext context = FacesContext.getCurrentInstance();
	 * context.getViewRoot().setLocale(locale); }
	 */
}
