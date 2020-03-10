package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

import com.apirest.MedicamentoSolidario.Models.PontoColeta;

public class PontoColetaDTO {
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
	
	public PontoColeta transformarParaObjSalvar() {
		return new PontoColeta(nome, cnpj, cidade, estado, rua, bairro,
				numero, complemento, cep, atividadePrincipal, dataCadastro);
	}
	public PontoColeta transformarParaObjEditar() {
		return new PontoColeta(id, nome, cnpj, cidade, estado, rua,
				bairro, numero, complemento, cep, atividadePrincipal, dataCadastro);
	}
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
	
}
