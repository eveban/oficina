package br.com.angelellirh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADO")
public class Estado implements Serializable {

	@Override
	public String toString() {
		return nome;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estcod")
	private Long id;

	@Column(name = "estsig")
	private char sigla;

	@Column(name = "estnom")
	private String nome;
	
	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY)
	private List<Cidade> cidades = new ArrayList<Cidade>();

	public Long getId() {
		return id;
	}

	public char getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	

}
