package br.com.angelellirh.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.angelellirh.dao.DAO;
import br.com.angelellirh.dao.ProfissionalDAO;
import br.com.angelellirh.model.Curso;
import br.com.angelellirh.model.Experiencia;
import br.com.angelellirh.model.Profissional;

@SessionScoped
@ManagedBean(name = "profissionalMB")
public class ProfissionalMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Profissional profissional = new Profissional();
	private Experiencia experiencia = new Experiencia();
	private Curso curso = new Curso();
	private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
	private Long cargoId;

	public String novo() {
		// this.destinoSalvar = "usuarioSucesso";
		this.profissional = new Profissional();
		this.profissional.setAtivo(true);
		return "cadastro";
	}

	public String salvar() {
		Integer codigo = profissional.getId();
		if (codigo == null || codigo == 0) {
			profissional.getPermissao().add("ROLE_USUARIO");
			this.profissionalDAO.salvar(profissional);
		} else {
			this.profissionalDAO.atualiza(profissional);
		}
		this.profissional = new Profissional();
		return "";
	}

	public String adiciona() {
		/*
		 * FacesContext context = FacesContext.getCurrentInstance(); String
		 * senha = this.profissional.getSenha(); if
		 * (!senha.equals(this.confirmaSenha)) { FacesMessage facesMessage = new
		 * FacesMessage( "Confirmação da senha é diferente da senha.");
		 * context.addMessage(null, facesMessage); return null; }
		 */
		DAO<Profissional> dao = new DAO<Profissional>(Profissional.class);
		// ProfissionalDAO dao = new ProfissionalDAO();
		dao.adiciona(profissional);
		this.profissional = new Profissional();
		return "";
	}

	public Profissional carregar(Integer codigo) {
		return this.profissionalDAO.carregar(codigo);
	}

	public Profissional buscaPorLogin(String login) {
		return this.profissionalDAO.buscaPorLogin(login);
	}

	public void excluir(Profissional profissional) {
		this.profissionalDAO.remove(profissional);
	}

	public List<Profissional> listar() {
		return this.profissionalDAO.listaTodos();
	}

	public void adicionaExperiencia() {
		experiencia.setProfissional(profissional);
		profissional.adicionaExperiencia(experiencia);
		experiencia = new Experiencia();
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	public ProfissionalDAO getProfissionalDAO() {
		return profissionalDAO;
	}

	public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
		this.profissionalDAO = profissionalDAO;
	}

}
