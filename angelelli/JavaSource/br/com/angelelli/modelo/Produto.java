package br.com.angelelli.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "idobservacao")
	private Observacao observacao;

	@ManyToOne
	@JoinColumn(name = "idrma")
	private RMA rma = new RMA();

	@ManyToOne
	@JoinColumn(name = "idclassificacao", nullable = false, updatable = true)
	private Classificacao classificacao;

	@ManyToOne
	@JoinColumn(name = "idembalagem")
	private Embalagem embalagem;

	@Column(name = "codigoauxiliar")
	private Integer codigoAuxiliar;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "valorcalorico")
	private Integer valorCalorico;

	@Column(name = "carboidratos")
	private Integer carboidratos;

	@Column(name = "fibras")
	private Integer fibra;

	@Column(name = "proteinas")
	private Integer proteina;

	@Column(name = "gordurastotais")
	private Integer gorduraTotal;

	@Column(name = "gordurassaturadas")
	private Integer gorduraSaturada;

	@Column(name = "gorduratrans")
	private Integer gorduraTrans;

	@Column(name = "sodio")
	private Integer sodio;

	@Column(name = "gluten")
	private String gluten;

	@Column(name = "ingrediente")
	private String ingrediente;

	@Column(name = "percgordurasaturada", precision = 11, scale = 2)
	private Double percentualGorduraSaturada;

	@Column(name = "percgorduratrans", precision = 11, scale = 2)
	private Double percentualGorduraTrans;

	@Column(name = "percproteinas", precision = 11, scale = 2)
	private Double percentualProteina;

	@Column(name = "percgordurastotais", precision = 11, scale = 2)
	private Double percentualGorduraTotal;

	@Column(name = "perccarboidratos", precision = 11, scale = 2)
	private Double percentualCarboidrato;

	@Column(name = "percfibras", precision = 11, scale = 2)
	private Double percentualFibras;

	@Column(name = "percsodio", precision = 11, scale = 2)
	private Double percentualSodio;

	@Column(name = "percvalorcalorico", precision = 11, scale = 2)
	private Double percentualValorCalorico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Observacao getObservacao() {
		return observacao;
	}

	public void setObservacao(Observacao observacao) {
		this.observacao = observacao;
	}

	public RMA getRma() {
		return rma;
	}

	public void setRma(RMA rma) {
		this.rma = rma;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Embalagem getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}

	public Integer getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(Integer codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getValorCalorico() {
		return valorCalorico;
	}

	public void setValorCalorico(Integer valorCalorico) {
		this.valorCalorico = valorCalorico;
	}

	public Integer getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(Integer carboidratos) {
		this.carboidratos = carboidratos;
	}

	public Integer getFibra() {
		return fibra;
	}

	public void setFibra(Integer fibra) {
		this.fibra = fibra;
	}

	public Integer getProteina() {
		return proteina;
	}

	public void setProteina(Integer proteina) {
		this.proteina = proteina;
	}

	public Integer getGorduraTotal() {
		return gorduraTotal;
	}

	public void setGorduraTotal(Integer gorduraTotal) {
		this.gorduraTotal = gorduraTotal;
	}

	public Integer getGorduraSaturada() {
		return gorduraSaturada;
	}

	public void setGorduraSaturada(Integer gorduraSaturada) {
		this.gorduraSaturada = gorduraSaturada;
	}

	public Integer getGorduraTrans() {
		return gorduraTrans;
	}

	public void setGorduraTrans(Integer gorduraTrans) {
		this.gorduraTrans = gorduraTrans;
	}

	public Integer getSodio() {
		return sodio;
	}

	public void setSodio(Integer sodio) {
		this.sodio = sodio;
	}

	public String getGluten() {
		return gluten;
	}

	public void setGluten(String gluten) {
		this.gluten = gluten;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Double getPercentualGorduraSaturada() {
		return percentualGorduraSaturada;
	}

	public void setPercentualGorduraSaturada(Double percentualGorduraSaturada) {
		this.percentualGorduraSaturada = percentualGorduraSaturada;
	}

	public Double getPercentualGorduraTrans() {
		return percentualGorduraTrans;
	}

	public void setPercentualGorduraTrans(Double percentualGorduraTrans) {
		this.percentualGorduraTrans = percentualGorduraTrans;
	}

	public Double getPercentualProteina() {
		return percentualProteina;
	}

	public void setPercentualProteina(Double percentualProteina) {
		this.percentualProteina = percentualProteina;
	}

	public Double getPercentualGorduraTotal() {
		return percentualGorduraTotal;
	}

	public void setPercentualGorduraTotal(Double percentualGorduraTotal) {
		this.percentualGorduraTotal = percentualGorduraTotal;
	}

	public Double getPercentualCarboidrato() {
		return percentualCarboidrato;
	}

	public void setPercentualCarboidrato(Double percentualCarboidrato) {
		this.percentualCarboidrato = percentualCarboidrato;
	}

	public Double getPercentualFibras() {
		return percentualFibras;
	}

	public void setPercentualFibras(Double percentualFibras) {
		this.percentualFibras = percentualFibras;
	}

	public Double getPercentualSodio() {
		return percentualSodio;
	}

	public void setPercentualSodio(Double percentualSodio) {
		this.percentualSodio = percentualSodio;
	}

	public Double getPercentualValorCalorico() {
		return percentualValorCalorico;
	}

	public void setPercentualValorCalorico(Double percentualValorCalorico) {
		this.percentualValorCalorico = percentualValorCalorico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
