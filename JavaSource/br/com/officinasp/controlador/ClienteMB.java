package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.simple.parser.ParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Cidade;
import br.com.officinasp.modelo.Cliente;
import br.com.officinasp.util.buscaCep;

@ManagedBean(name = "clientesMB")
@SessionScoped
public class ClienteMB implements Serializable {

	@ManagedProperty(value = "#{usuariosMB}")
	private UsuarioMB usuariosMB;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private LazyDataModel<Cliente> listaClientes;
	private List<Cliente> listClientes;
	private Cliente clienteSelected;
	private Cidade cidadeSelected;
	private int idEmpresa;
	private String localizacao;
	private MapModel locais;

	public ClienteMB() {
		super();
	}

	public void salvar() throws IOException {

		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		cliente.setCidade(cidadeSelected);
		if (cliente.getId() == 0) {
			dao.adiciona(cliente);
		} else {
			dao.atualiza(cliente);
		}
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso ", "OfficinaSP");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public String novocliente() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		cliente = new Cliente();
		cliente.setStatus_cliente(true);
		// cliente.setCliente_desde(formatador.format(data));
		// cliente.setId_empresa(usuariosMB.getSelectedUsu().getId_empresa());
		locais = null;
		return "cadCliente";
	}

	public String editar(Cliente cliente) {
		this.cliente = cliente;
		this.setCidadeSelected(this.cliente.getCidade());
		return "cadCliente";
	}

	public void excluir(Cliente cliente) {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		this.cliente = dao.buscaPorId(clienteSelected.getId());
		dao.remove(this.cliente);
	}

	public void setListaClientes(LazyDataModel<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(Cliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public List<Cliente> getListClientes() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		listClientes = dao.getLista("Select f from Clientes f order by f.nome");
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	/*
	 * public int getIdEmpresa() { HttpSession session = (HttpSession)
	 * FacesContext.getCurrentInstance()
	 * .getExternalContext().getSession(false); idEmpresa = (int)
	 * session.getAttribute("idEmpresa"); return idEmpresa; }
	 */

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public UsuarioMB getUsuariosMB() {
		return usuariosMB;
	}

	public void setUsuariosMB(UsuarioMB usuariosMB) {
		this.usuariosMB = usuariosMB;
	}

	public void localizaCep(String CEP) throws IOException, ParseException {
		System.out.println("Localizando o cep: " + CEP);
		buscaCep localizaCep = new buscaCep();
		cliente.setRua(localizaCep.getEndereco(CEP));
		cliente.setBairro(localizaCep.getBairro(CEP));
		// cliente.setCidade(localizaCep.getCidade(CEP));
		// cliente.setUf(localizaCep.getUF(CEP));
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	private MapModel carregarLocais() throws IOException, ParseException {
		locais = new DefaultMapModel();
		if (cliente.getCep().length() > 8) {
			buscaCep localizaCep = new buscaCep();
			localizacao = localizaCep.getLatLong(cliente.getCep());
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
		if (cliente.getCep() != null && locais == null) {
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
		RequestContext.getCurrentInstance().execute("PF('dlg').show()");
	}

	public Cidade getCidadeSelected() {
		return cidadeSelected;
	}

	public void setCidadeSelected(Cidade cidadeSelected) {
		this.cidadeSelected = cidadeSelected;
	}

}
