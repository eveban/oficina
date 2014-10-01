package br.com.officinasp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Marca;
import br.com.officinasp.modelo.Modelo;

@ManagedBean(name = "modelosMB")
@ViewScoped
public class ModeloMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Modelo modelo = new Modelo();
	private LazyDataModel<Modelo> listaModelos;
	private List<Modelo> listModelos;
	private Modelo modeloSelected;
	private List<Marca> listaMarcas;
	private Marca marcaSelected;

	public ModeloMB() {
		super();
	}

	public void salvar() throws IOException {
		DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
		if (modelo.getId().length() >= 0) {
			dao.adiciona(modelo);
		} else {
			dao.atualiza(modelo);
		}
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso ", "OfficinaSP");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public String novoModelos() {
		modelo = new Modelo();
		return "cadModelos";
	}

	public String editar(Modelo modelo) {
		this.modelo = modelo;
		return "cadModelos";
	}

	public void excluir(Modelo modelo) {
		DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
		this.modelo = dao.buscaPorId(modeloSelected.getId());
		dao.remove(this.modelo);
	}

	public LazyDataModel<Modelo> getListaModelos() {

		if (listaModelos == null) {
			listaModelos = new LazyDataModel<Modelo>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public List<Modelo> load(int startingAt, int maxPerPage,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {

					DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
					List<Modelo> pesquisa = dao.getListaPaginado(startingAt,
							maxPerPage, "nome");
					return pesquisa;
				}

			};
			DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
			int totalRowCount = dao.count();
			listaModelos.setRowCount(totalRowCount);
		}
		return listaModelos;
	}

	public void setListaModelos(LazyDataModel<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Modelo> getListModelos() {
		DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);
		if (marcaSelected == null) {
			System.out.println("Listando os modelos do veiculo (ALL)");
			listModelos = dao
					.getLista("Select f from Modelos f order by f.nome");
		} else {
			System.out.println("Listando os modelos do veiculo da Marca:"
					+ marcaSelected.getId());
			listModelos = dao
					.getLista("Select f from Modelos f where f.marca.id= "
							+ marcaSelected.getId() + " order by f.nome");
		}
		return listModelos;
	}

	public void setListModelos(List<Modelo> listModelos) {
		this.listModelos = listModelos;
	}

	public Modelo getModeloSelected() {
		return modeloSelected;
	}

	public void setModeloSelected(Modelo modeloSelected) {
		this.modeloSelected = modeloSelected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Marca> getListaMarcas() {
		DAO<Marca> dao = new DAO<Marca>(Marca.class);
		listaMarcas = dao.getLista("Select f from Marcas f order by f.nome");
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public Marca getMarcaSelected() {
		return marcaSelected;
	}

	public void setMarcaSelected(Marca marcaSelected) {
		this.marcaSelected = marcaSelected;
	}

}
