package br.com.officinasp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sexo", length = 10)
	private String sexo;

	@Temporal(TemporalType.DATE)
	private Calendar dt_cadastro = Calendar.getInstance();

	@Column(name = "razao", length = 50)
	private String razao;

	@Column(name = "fantasia", length = 50)
	private String nomeFantasia;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "senha", length = 20)
	private String senha;

	@Column(name = "ddd", length = 3)
	private String ddd;

	@Column(name = "telefone", length = 11)
	private String telefone;

	@Column(name = "dddc", length = 3)
	private String dddc;

	@Column(name = "celular", length = 11)
	private String celular;

	@Column(name = "cpf_cnpj", length = 20)
	private String cpf_cnpj;

	@Column(name = "apelido_responsavel", length = 30)
	private String apelidoResponsavel;

	@Column(name = "rg", length = 15)
	private String rg;

	@Column(name = "inscricaoestadual", length = 30)
	private String inscricaoEstadual;

	@Column(name = "nascimento")
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento = Calendar.getInstance();

	@Column(name = "rua", length = 50)
	private String rua;

	@Column(name = "bairro", length = 30)
	private String bairro;

	@Column(name = "numero", length = 10)
	private String numero;

	@Column(name = "complemento", length = 30)
	private String complemento;

	@Column(name = "cep", length = 12)
	private String cep;

	// private String uf;

	@Column(name = "nextel", length = 15)
	private String nextel;

	@Column(name = "fax", length = 20)
	private String fax;

	@Column(name = "pabx", length = 20)
	private String pabx;

	@Column(name = "comercial", length = 50)
	private String telefoneComercial;

	@Column(name = "site", length = 50)
	private String site;

	@Temporal(TemporalType.DATE)
	private Calendar clienteDesde = Calendar.getInstance();

	@Column(name = "restricao")
	public boolean restricao;

	@Column(name = "obs", length = 120)
	private String obs;

	@Column(name = "tipoCliente")
	private int tipoCliente;

	@Column(name = "status")
	private boolean status_cliente;

	@Column(name = "telefone2", length = 20)
	private String telefone2;

	@Column(name = "celular2", length = 20)
	private String celular2;

	@Column(name = "nextel2", length = 20)
	private String nextel2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "idcidade", nullable = false)
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "idlogradouro", nullable = false)
	private Logradouro logradouro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
	@Column(name = "codigo")
	private List<Veiculo> veiculos;

	public void adicionaVeiculo(Veiculo veiculo) {
		if (this.veiculos == null) {
			this.veiculos = new ArrayList<Veiculo>();
		}
		this.veiculos.add(veiculo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Calendar dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDddc() {
		return dddc;
	}

	public void setDddc(String dddc) {
		this.dddc = dddc;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getApelidoResponsavel() {
		return apelidoResponsavel;
	}

	public void setApelidoResponsavel(String apelidoResponsavel) {
		this.apelidoResponsavel = apelidoResponsavel;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNextel() {
		return nextel;
	}

	public void setNextel(String nextel) {
		this.nextel = nextel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPabx() {
		return pabx;
	}

	public void setPabx(String pabx) {
		this.pabx = pabx;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Calendar getClienteDesde() {
		return clienteDesde;
	}

	public void setClienteDesde(Calendar clienteDesde) {
		this.clienteDesde = clienteDesde;
	}

	public boolean isRestricao() {
		return restricao;
	}

	public void setRestricao(boolean restricao) {
		this.restricao = restricao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public boolean isStatus_cliente() {
		return status_cliente;
	}

	public void setStatus_cliente(boolean status_cliente) {
		this.status_cliente = status_cliente;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public String getNextel2() {
		return nextel2;
	}

	public void setNextel2(String nextel2) {
		this.nextel2 = nextel2;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
