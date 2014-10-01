package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Usuario;

@ManagedBean(name = "usuariosMB")
@RequestScoped
public class UsuarioMB implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String cpf_user;
	private String senha_user;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Usuario selectedUsu;
	private int tipoUsuario;

	public UsuarioMB() {
		super();
	}
	
	public void salvar() throws IOException {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		if (usuario.getId() == 0) {
			dao.adiciona(usuario);
		} else {
			dao.atualiza(usuario);
		}
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso ", "OfficinaSP");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public String novoUsuario() {
		usuario = new Usuario();
		return "cadUsuario";
	}

	public String editar(Usuario usuario) {
		this.usuario = usuario;
		return "cadUsuario";
	}

	public String excluir(Usuario usuario) {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		this.usuario = usuario;
		dao.remove(usuario);
		return "listarUsuarios";
	}

	public void listagemUsuarios() throws IOException {
		if (getTipoUsuario() == 1) {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/officinas/main.jsf");
		} else {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpSession session = (HttpSession) externalContext
					.getSession(false);
			int idUsuario = (Integer) session.getAttribute("codigoUser");
			DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			usuario = dao.buscaPorId(idUsuario);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/officinas/main.jsf");
		}
	}

	public String verificaLog() {
		String retorno = null;

		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		List<Usuario> result = dao
				.getLista("select o from Usuarios o where o.cpf ='" + cpf_user
						+ "' and o.senha = '" + senha_user.toUpperCase() + "'");
		if (result.size() > 0) {

			int idUsuario = (int) result.get(0).getId();
			this.selectedUsu = dao.buscaPorId(idUsuario);

			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();

			session.setAttribute("currentUser", cpf_user);
			session.setAttribute("codigoUser", idUsuario);
//			session.setAttribute("idEmpresa", selectedUsu.getId_empresa());

			retorno = "main.jsf";
		} else {
			retorno = "Usu�rio ou senha incorretos!";
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"OficinaSP - ", retorno));
			retorno = "login.jsf";
		}
		return retorno;
	}

	public String getCpf_user() {
		return cpf_user;
	}

	public void setCpf_user(String cpf_user) {
		this.cpf_user = cpf_user.replaceAll("\\D", "");
	}

	public String getSenha_user() {
		return senha_user;
	}

	public void setSenha_user(String senha_user) {
		this.senha_user = senha_user;
	}

	public String logoff() {
		/*
		 * Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
		 * .getExternalContext().getSessionMap(); sessionMap.clear();
		 */

		System.out.println("Executando o logoff");
		FacesContext conext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) conext.getExternalContext()
				.getSession(false);
		session.invalidate();
		return "login.jsf";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Usuario> getListaUsuarios() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);

		listaUsuarios = dao
				.getLista("select o from Usuarios o order by o.login");
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getSelectedUsu() {
		return selectedUsu;
	}

	public void setSelectedUsu(Usuario selectedUsu) {
		this.selectedUsu = selectedUsu;
	}

	public int getTipoUsuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		int tipoUsuario = (Integer) session.getAttribute("admin");
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
