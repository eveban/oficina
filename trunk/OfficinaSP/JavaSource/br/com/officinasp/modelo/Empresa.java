package br.com.officinasp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro = Calendar.getInstance();

	@Column(name = "tipoempresa")
	private int tipoEmpresa;

	@Column(name = "nomefantasia")
	private String nomeFantasia;

	@Column(name = "razao", length = 50)
	private String razaoSocial;

	@Column(name = "cnpj", length = 20)
	private String cnpj;

	@Column(name = "inscricao_estadual", length = 20)
	private String inscricaoEstadual;

	@Column(name = "nome", length = 30)
	private String nomeResponsavel;

	@Column(name = "cpfresponsavel")
	private String cpf_responsavel;

	@Column(name = "endereco", length = 50)
	private String endereco;

	@Column(name = "numero", length = 10)
	private String numero;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep", length = 12)
	private String cep;

	@Column(name = "telefone1", length = 15)
	private String telefone1;

	@Column(name = "telefone2", length = 15)
	private String telefone2;

	@Column(name = "celular", length = 15)
	private String celular;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "site", length = 30)
	private String site;

	private String complemento;

	@Column(name = "segmento", length = 20)
	private String segmento;

	@Column(name = "obs", length = 120)
	private String obs;

	private boolean permissao;
	private boolean sms;
	private boolean nfe;
	private boolean pagseguro;
	private boolean danfe;
	private boolean status;
	private String logo;
	private boolean ligado;
	private int limite_sms;
	private Double valor_sms;
	private String sms_extra;

	@Column(name = "im", length = 30)
	private String im;

	@Column(name = "cnae", length = 20)
	private String cnae;

	@Column(name = "crt", length = 20)
	private String crt;

	@ManyToOne
	@JoinColumn(name = "idcidade")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "idlogradouro")
	private Logradouro logradouro;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.PERSIST)
	@Column(name = "idempresa")
	private List<Usuario> usuarios;

	public void adicionaUsuario(Usuario usuario) {
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<Usuario>();
		}
		this.usuarios.add(usuario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(int tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpf_responsavel() {
		return cpf_responsavel;
	}

	public void setCpf_responsavel(String cpf_responsavel) {
		this.cpf_responsavel = cpf_responsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isPermissao() {
		return permissao;
	}

	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isNfe() {
		return nfe;
	}

	public void setNfe(boolean nfe) {
		this.nfe = nfe;
	}

	public boolean isPagseguro() {
		return pagseguro;
	}

	public void setPagseguro(boolean pagseguro) {
		this.pagseguro = pagseguro;
	}

	public boolean isDanfe() {
		return danfe;
	}

	public void setDanfe(boolean danfe) {
		this.danfe = danfe;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}

	public int getLimite_sms() {
		return limite_sms;
	}

	public void setLimite_sms(int limite_sms) {
		this.limite_sms = limite_sms;
	}

	public Double getValor_sms() {
		return valor_sms;
	}

	public void setValor_sms(Double valor_sms) {
		this.valor_sms = valor_sms;
	}

	public String getSms_extra() {
		return sms_extra;
	}

	public void setSms_extra(String sms_extra) {
		this.sms_extra = sms_extra;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getCrt() {
		return crt;
	}

	public void setCrt(String crt) {
		this.crt = crt;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		Empresa other = (Empresa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
