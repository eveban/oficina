package br.com.angelelli.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.angelelli.dao.DAO;
import br.com.angelelli.dao.ProdutoDAO;
import br.com.angelelli.modelo.Classificacao;
import br.com.angelelli.modelo.Embalagem;
import br.com.angelelli.modelo.Observacao;
import br.com.angelelli.modelo.Produto;
import br.com.angelelli.modelo.RMA;

@ViewScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Classificacao> classificacoes;
	private List<Observacao> observacoes;
	private List<Embalagem> embalagens;
	private Classificacao classificacaoSelecionada;
	private List<SelectItem> listaClassificacao;
	private List<RMA> rmas;
	private List<Produto> produtos;
	private Boolean modoEdicao;

	// Listagem adicionada para novo Evento do primefaces no Datatable
	// -filteredValue
	private List<Produto> listaProdutos;

	public void grava() {
		DAO<Produto> produtoDAO = new DAO<Produto>(Produto.class);
		// produto.setClassificacao(classificacaoSelecionada);
		if (this.produto.getId() == 0) {
			produtoDAO.adiciona(this.produto);
		} else {
			produtoDAO.atualiza(this.produto);
		}
		this.produto = new Produto();
		this.produtos = new ProdutoDAO().listaProdutoOrdem();
	}

	public void salvarCopia() {
		DAO<Produto> produtoDAO = new DAO<Produto>(Produto.class);
		produtoDAO.adiciona(produto);
		this.produtos = new ProdutoDAO().listaProdutoOrdem();

	}

	public void atualizar(Produto produto) {
		// Classificacao classificacao = this.produto.getClassificacao();
		this.produto = produto;
		// this.setClassificacaoSelecionada(classificacao);
		System.out.println("ID: " + produto.getId());
	}

	public void remover() {
		DAO<Produto> produtoDAO = new DAO<Produto>(Produto.class);
		produtoDAO.remove(produto);
		this.produtos = new ProdutoDAO().listaProdutoOrdem();
		this.produto = new Produto();
	}

	@PostConstruct
	public void listaProdutos() {
		System.out.println("Carregando produtos...");
		this.produtos = new ProdutoDAO().listaProdutoOrdem();
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public List<SelectItem> getListaClassificacao() {
		if (this.listaClassificacao == null) {
			this.listaClassificacao = new ArrayList<SelectItem>();
			DAO<Classificacao> classificacaoDAO = new DAO<Classificacao>(
					Classificacao.class);
			List<Classificacao> cla = classificacaoDAO.listaTodos();
			for (Classificacao classificacao : cla) {
				SelectItem item = new SelectItem(classificacao.getId(),
						classificacao.getDescricao());

				this.listaClassificacao.add(item);
			}

		}
		return listaClassificacao;
	}

	public List<RMA> getRmas() {
		if (this.rmas == null) {
			DAO<RMA> rmaDAO = new DAO<RMA>(RMA.class);
			this.rmas = rmaDAO.listaTodos();
		}
		return this.rmas;
	}

	public List<Classificacao> getClassificacoes() {
		if (this.classificacoes == null) {
			DAO<Classificacao> classificacaoDAO = new DAO<Classificacao>(
					Classificacao.class);
			this.classificacoes = classificacaoDAO.lista();
		}
		return this.classificacoes;
	}

	public List<Observacao> getObservacoes() {
		if (this.observacoes == null) {
			DAO<Observacao> observacaoDAO = new DAO<Observacao>(
					Observacao.class);
			this.observacoes = observacaoDAO.listaTodos();
		}
		return this.observacoes;
	}

	public List<Embalagem> getEmbalagens() {
		if (this.embalagens == null) {
			DAO<Embalagem> embalagemDAO = new DAO<Embalagem>(Embalagem.class);
			this.embalagens = embalagemDAO.listaTodos();
		}
		return embalagens;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
			produto.setClassificacao(new Classificacao());
		}

		return produto;
	}

	public void setListaClassificacao(List<SelectItem> listaClassificacao) {
		this.listaClassificacao = listaClassificacao;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(Boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public Classificacao getClassificacaoSelecionada() {
		return classificacaoSelecionada;
	}

	public void setClassificacaoSelecionada(
			Classificacao classificacaoSelecionada) {
		this.classificacaoSelecionada = classificacaoSelecionada;
	}

}
