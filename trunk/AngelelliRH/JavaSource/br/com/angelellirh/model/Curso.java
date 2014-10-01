package br.com.angelellirh.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CURSO")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codCurso")
	private int id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "cargahoraria")
	private int cargaHoraria;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicial")
	private Calendar dataInicial = Calendar.getInstance();

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFinal")
	private Calendar dataFinal = Calendar.getInstance();

	@ManyToOne
	private Profissional profissional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
