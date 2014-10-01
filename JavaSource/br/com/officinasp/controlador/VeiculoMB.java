package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Marca;
import br.com.officinasp.modelo.Modelo;
import br.com.officinasp.modelo.Veiculo;

@ManagedBean(name = "veiculosMB")
@ViewScoped
public class VeiculoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{clientesMB}")
	private ClienteMB clientesMB;

	private Veiculo veiculo = new Veiculo();
	private LazyDataModel<Veiculo> listaVeiculos;
	private List<Veiculo> listVeiculos;
	private Veiculo veiculoSelected;
	private Modelo modeloSelected;
	private Marca marcaSelected;
	private List<Modelo> listaModelos;
	private int idEmpresa;

	public VeiculoMB() {
		super();
	}

	public void salvar() throws IOException {
		System.out.println("salvando veiculo");
		DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
		veiculo.setCliente(clientesMB.getCliente());
		veiculo.setModelo(modeloSelected);

		try {
			if (veiculo.getId() == 0) {
				dao.adiciona(veiculo);
			} else {
				dao.atualiza(veiculo);
			}
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Registro salvo com sucesso ",
					"OfficinaSP");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, e.getMessage(), "OfficinaSP");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		}
	}

	public void novoVeiculos() {
		System.out.println("Inserindo ve�culo");
		veiculo = new Veiculo();
		veiculo.setStatus(true);
		veiculo.setDt_cadastro(new Date());
		veiculo.setId_empresa(getIdEmpresa());
		this.setModeloSelected(null);
		this.setMarcaSelected(null);
	}

	public void editar(Veiculo veiculo) {
		this.veiculo = veiculo;
		this.setModeloSelected(veiculo.getModelo());
		this.setMarcaSelected(modeloSelected.getMarca());
		carregaListaModelos(marcaSelected);
	}

	public void excluir(Veiculo veiculo) {
		DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
		this.veiculo = dao.buscaPorId(veiculoSelected.getId());
		dao.remove(this.veiculo);
	}

	public LazyDataModel<Veiculo> getListaVeiculos() {

		if (listaVeiculos == null) {
			listaVeiculos = new LazyDataModel<Veiculo>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public List<Veiculo> load(int startingAt, int maxPerPage,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {

					DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
					List<Veiculo> pesquisa = dao.getListaPaginado(startingAt,
							maxPerPage,
							// " id_empresa = " + clientesMB.getIdEmpresa(),
							"nome");
					return pesquisa;
				}

			};
			DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
			int totalRowCount = dao.count();
			listaVeiculos.setRowCount(totalRowCount);
		}
		return listaVeiculos;
	}

	public void setListaVeiculos(LazyDataModel<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public List<Veiculo> getListVeiculos() {
		if (clientesMB != null) {
			DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
			listVeiculos = dao
					.getLista("Select f from Veiculos f where f.cliente.id = "
							+ clientesMB.getCliente().getId()
							+ " and f.id_empresa = " + getIdEmpresa()
							+ " order by f.placa");
		}
		return listVeiculos;
	}

	public void setListVeiculos(List<Veiculo> listVeiculos) {
		this.listVeiculos = listVeiculos;
	}

	public ClienteMB getClientesMB() {
		return clientesMB;
	}

	public void setClientesMB(ClienteMB clientesMB) {
		this.clientesMB = clientesMB;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Veiculo getVeiculoSelected() {
		return veiculoSelected;
	}

	public void setVeiculoSelected(Veiculo veiculoSelected) {
		this.veiculoSelected = veiculoSelected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdEmpresa() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		// idEmpresa = (int) session.getAttribute("idEmpresa");
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Modelo getModeloSelected() {
		return modeloSelected;
	}

	public void setModeloSelected(Modelo modeloSelected) {
		this.modeloSelected = modeloSelected;
	}

	public void carregaListaModelos(Marca marcaSelected) {
		DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
		if (this.marcaSelected == null) {
			System.out.println("Listando os modelos do veiculo (ALL)");
			setListaModelos(null);
		} else {
			System.out.println("Listando os modelos do veiculo da Marca:"
					+ this.marcaSelected.getId());
			listaModelos = dao
					.getLista("Select f from Modelos f where f.marca.id= "
							+ this.marcaSelected.getId() + " order by f.nome");
		}
	}

	public Marca getMarcaSelected() {
		return marcaSelected;
	}

	public void setMarcaSelected(Marca marcaSelected) {
		this.marcaSelected = marcaSelected;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

}
