package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.officinasp.dao.CidadeDAO;
import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Cidade;
import br.com.officinasp.modelo.Estado;

@ViewScoped
@ManagedBean(name = "cidadesMB")
public class CidadesMB implements Serializable {

	private Cidade cidade = new Cidade();
	private Estado estado = new Estado();
	private LazyDataModel<Cidade> listaCidades;
	private List<Cidade> listCidades;
	private Cidade cidadeSelected;
	private List<SelectItem> cidades = new ArrayList<SelectItem>();
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		if (this.estados == null) {
			DAO<Estado> estadoDAO = new DAO<Estado>(Estado.class);
			this.estados = estadoDAO.getAll();
		}
		return this.estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void actionCarregaCidades() {
		System.out.println("id do estado selecionado >>> "
				+ this.estado.getId());

		this.cidades = this.getCidadesByEstado();
	}

	public void actionCarregaListaCidades() {
		System.out.println("id do estado selecionado >>> "
				+ this.estado.getId());

		this.listCidades = this.getCidadesByEstadoTeste();
	}

	public List<Cidade> getCidadesByEstadoTeste() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		this.listCidades = cidadeDAO.getCidadesByEstadoTeste(this.estado
				.getId());
		return this.listCidades;
	}

	public List<SelectItem> getCidadesByEstado() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO
				.getCidadesByEstado(this.estado.getId());
		List<SelectItem> itens = new ArrayList<SelectItem>(cidades.size());
		for (Cidade c : cidades) {
			itens.add(new SelectItem(c.getId(), c.getNome()));
		}
		return itens;
	}

	public void salvar() throws IOException {
		DAO<Cidade> dao = new DAO<Cidade>(Cidade.class);
		if (cidade.getId() == 0) {
			dao.adiciona(cidade);
		} else {
			dao.atualiza(cidade);
		}
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso ", "OfficinaSP");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public String novaCidade() {
		cidade = new Cidade();
		return "cadCidade";
	}

	public String editar(Cidade cidade) {
		this.cidade = cidade;
		return "cadCidade";
	}

	public void excluir(Cidade cidade) {
		DAO<Cidade> dao = new DAO<Cidade>(Cidade.class);
		this.cidade = dao.buscaPorId(cidadeSelected.getId());
		dao.remove(this.cidade);
	}

	public LazyDataModel<Cidade> getListaCidades() {

		if (listaCidades == null) {
			listaCidades = new LazyDataModel<Cidade>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public List<Cidade> load(int startingAt, int maxPerPage,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {

					DAO<Cidade> dao = new DAO<Cidade>(Cidade.class);
					List<Cidade> pesquisa = dao.getListaPaginado(startingAt,
							maxPerPage, "nome");
					return pesquisa;

				}

			};
			DAO<Cidade> dao = new DAO<Cidade>(Cidade.class);
			int totalRowCount = dao.count();
			listaCidades.setRowCount(totalRowCount);
		}
		return listaCidades;
	}

	public void setListaCidades(LazyDataModel<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<Cidade> getListCidades() {
		this.listCidades = this.getCidadesByEstadoTeste();
		return this.listCidades;
	}

	public void setListCidades(List<Cidade> listCidades) {
		this.listCidades = listCidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cidade getCidadeSelected() {
		return cidadeSelected;
	}

	public void setCidadeSelected(Cidade cidadeSelected) {
		this.cidadeSelected = cidadeSelected;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

}
