package br.com.officinasp.controlador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Estado;

@ManagedBean(name = "estadoMB")
@ViewScoped
public class EstadoMB {

	private Estado estado = new Estado();

	private List<Estado> estados;

	public List<Estado> getEstados() {
		if (this.estados == null) {
			DAO<Estado> estadoDAO = new DAO<Estado>(Estado.class);
			this.estados = estadoDAO.getAll();
		}
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
