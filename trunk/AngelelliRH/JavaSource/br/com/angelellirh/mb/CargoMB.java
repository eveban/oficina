package br.com.angelellirh.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.angelellirh.dao.DAO;
import br.com.angelellirh.model.Cargo;

@ManagedBean(name = "cargoMB")
@ViewScoped
public class CargoMB implements Serializable {
	private Cargo cargo = new Cargo();
	private List<Cargo> cargos;

	public List<Cargo> getCargos() {
		if (this.cargos == null) {
			DAO<Cargo> cargoDAO = new DAO<Cargo>(Cargo.class);
			this.cargos = cargoDAO.listaTodos();
		}
		return this.cargos;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}
