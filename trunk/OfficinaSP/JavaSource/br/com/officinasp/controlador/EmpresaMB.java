package br.com.officinasp.controlador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.officinasp.dao.EmpresaDAO;
import br.com.officinasp.modelo.Empresa;
import br.com.officinasp.modelo.Usuario;

@SessionScoped
@ManagedBean(name = "empresaMB")
public class EmpresaMB {

	private Empresa empresa = new Empresa();
	private Usuario usuario = new Usuario();
	private List<Empresa> empresas;

	public void salvar() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		if (this.empresa.getId() == 0) {
			empresaDAO.salvar(empresa);
		} else {
			empresaDAO.atualizar(empresa);
		}
		this.empresa = new Empresa();
	}

	public void novoUsuario() {
		// this.destinoSalvar = "usuarioSucesso";
		this.usuario.setAtivo(true);

	}

	public void adicionaUsuario() {
		usuario.getPermissao().add("ROLE_USUARIO");
		usuario.setAtivo(true);
		usuario.setEmpresa(empresa);
		empresa.adicionaUsuario(usuario);
		usuario = new Usuario();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
