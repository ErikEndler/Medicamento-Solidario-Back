package com.apirest.MedicamentoSolidario.Models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name="TB_PONTO_DE_COLETA")
public class PontoColeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String cnpj;
	private String cidade;
	private String estado;
	private String rua;
	private String bairro;
	private String numero;
	private String complemento;
	private String cep;
	private String atividadePrincipal;
	private Date dataCadastro;
	
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(mappedBy = "ponto")
	private List<Doacao> doacao;
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(mappedBy = "ponto")
	private List<Recebimento> doacao_out;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getAtividadePrincipal() {
		return atividadePrincipal;
	}

	public void setAtividadePrincipal(String atividadePrincipal) {
		this.atividadePrincipal = atividadePrincipal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Doacao> getDoacao() {
		return doacao;
	}

	public void setDoacao(List<Doacao> doacao) {
		this.doacao = doacao;
	}

	public List<Recebimento> getDoacao_out() {
		return doacao_out;
	}

	public void setDoacao_out(List<Recebimento> doacao_out) {
		this.doacao_out = doacao_out;
	}
	
	

	public PontoColeta() {
		super();
	}

	public PontoColeta(long id, String nome, String cnpj, String cidade, String estado, String rua, String bairro,
			String numero, String complemento, String cep, String atividadePrincipal, Date dataCadastro) {
		
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.estado = estado;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.atividadePrincipal = atividadePrincipal;
		this.dataCadastro = dataCadastro;
	}

	public PontoColeta(String nome, String cnpj, String cidade, String estado, String rua, String bairro, String numero,
			String complemento, String cep, String atividadePrincipal, Date dataCadastro) {
		
		this.nome = nome;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.estado = estado;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.atividadePrincipal = atividadePrincipal;
		this.dataCadastro = dataCadastro;
	}
	
}
