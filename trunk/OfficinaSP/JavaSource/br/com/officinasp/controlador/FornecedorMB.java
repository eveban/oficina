package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.json.simple.parser.ParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.officinasp.dao.FornecedorDAO;
import br.com.officinasp.modelo.Fornecedor;
import br.com.officinasp.util.ContextoUtil;
import br.com.officinasp.util.buscaCep;

@ManagedBean(name = "fornecedorBean")
@RequestScoped
public class FornecedorMB implements Serializable {

	private Fornecedor fornecedor = new Fornecedor();
	private List<Fornecedor> listaFornecedor;
	private String localizacao;
	private MapModel locais;

	public void novo() {
		this.fornecedor = new Fornecedor();
	}

	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		// this.fornecedorSelecionado.setUsuario(contextoBean.getUsuarioLogado());
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedorDAO.salvar(this.fornecedor);
		this.fornecedor = new Fornecedor();
		this.listaFornecedor = null;
	}

	public String editar() {
		return "cadFornecedor";
	}

	public void localizaCep(String CEP) throws IOException, ParseException {
		System.out.println("Localizando o cep: " + CEP);
		buscaCep localizaCep = new buscaCep();
		fornecedor.setRua(localizaCep.getEndereco(CEP));
		fornecedor.setBairro(localizaCep.getBairro(CEP));
		fornecedor.setCidade(localizaCep.getCidade(CEP));
		fornecedor.setUf(localizaCep.getUF(CEP));
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getListaFornecedor() {
		if (this.listaFornecedor == null) {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			this.listaFornecedor = fornecedorDAO.listaTodos();
		}
		return this.listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	private MapModel carregarLocais() throws IOException, ParseException {
		locais = new DefaultMapModel();
		if (fornecedor.getCep().length() > 8) {
			buscaCep localizaCep = new buscaCep();
			localizacao = localizaCep.getLatLong(fornecedor.getCep());
			if (localizacao != null) {

				String valor[] = localizacao.split(Pattern.quote(","));

				Double latitude = Double.parseDouble(valor[0]);
				Double longitude = Double.parseDouble(valor[1]);

				locais.addOverlay(new Marker(new LatLng(latitude, longitude),
						""));
			}
		}
		return locais;
	}

	public MapModel getLocais() throws IOException, ParseException {
		if (fornecedor.getCep() != null && locais == null) {
			carregarLocais();
		}
		return locais;
	}

	public void setLocais(MapModel locais) {
		this.locais = locais;
	}

	public void carregaLocal() throws IOException, ParseException {
		locais = null;
		getLocais();
		RequestContext.getCurrentInstance().execute("dlg.show()");
	}

}
