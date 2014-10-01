package br.com.angelelli.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.angelelli.dao.DAO;
import br.com.angelelli.modelo.Classificacao;

@ViewScoped
@ManagedBean
public class ClassificacaoBean {
	private Classificacao classificacao = new Classificacao();
	private List<Classificacao> classificacoes;
	private List<SelectItem> listaClassificacoes;

	@PostConstruct
	public void listaClassificacao() {
		System.out.println("Carregando classificações...");
		classificacoes = new DAO<Classificacao>(Classificacao.class)
				.listaTodos();
	}

	public List<SelectItem> getListaClassificacoes() {
		if (this.listaClassificacoes == null) {
			this.listaClassificacoes = new ArrayList<SelectItem>();
			DAO<Classificacao> classificacaoDAO = new DAO<Classificacao>(
					Classificacao.class);
			this.classificacoes = classificacaoDAO.listaTodos();

			for (Classificacao cla : this.classificacoes) {
				listaClassificacoes.add(new SelectItem(cla.getId(), cla
						.getDescricao()));
			}
		}
		return listaClassificacoes;
	}

	public void setListaClassificacoes(List<SelectItem> listaClassificacoes) {
		this.listaClassificacoes = listaClassificacoes;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public List<Classificacao> getClassificacoes() {
		return classificacoes;
	}

	public void setClassificacoes(List<Classificacao> classificacoes) {
		this.classificacoes = classificacoes;
	}

}
