package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MedicamentoDTO {
	
	private long id;
	private String nome;
	private String principio;
	private String tipoReceita;
	private Date data;
	private Date dataVencimento;
	private String tarja;
	private String tipoArmazenamento;
	private int quantidade;
	@JsonIgnore
	private Doacao fullDoacaoIn;
	private int idDoacaoIn;
	@JsonIgnore
	private Recebimento fullDoacaoOut;
	private int idDoacaoOut;
	
	//salvar medicamento sem interagir com as doaçoes
	public Medicamento transformarParaObjSalvar() {
		return new Medicamento(id, nome, principio, tipoReceita, data, dataVencimento, tarja, tipoArmazenamento, quantidade,idDoacaoIn);
	}
	//edita sem interagir com as doaçoes
	public Medicamento TransformarParaObjEditar() {
		return new Medicamento(nome, principio, tipoReceita, data, dataVencimento, tarja, tipoArmazenamento, quantidade);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrincipio() {
		return principio;
	}

	public void setPrincipio(String principio) {
		this.principio = principio;
	}

	public String getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(String tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getTarja() {
		return tarja;
	}

	public void setTarja(String tarja) {
		this.tarja = tarja;
	}

	public String getTipoArmazenamento() {
		return tipoArmazenamento;
	}

	public void setTipoArmazenamento(String tipoArmazenamento) {
		this.tipoArmazenamento = tipoArmazenamento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Doacao getFullDoacaoIn() {
		return fullDoacaoIn;
	}

	public void setFullDoacaoIn(Doacao fullDoacaoIn) {
		this.fullDoacaoIn = fullDoacaoIn;
	}

	public Recebimento getFullDoacaoOut() {
		return fullDoacaoOut;
	}

	public void setFullDoacaoOut(Recebimento fullDoacaoOut) {
		this.fullDoacaoOut = fullDoacaoOut;
	}

	public int getIdDoacaoIn() {
		return idDoacaoIn;
	}

	public void setIdDoacaoIn(int idDoacaoIn) {
		this.idDoacaoIn = idDoacaoIn;
	}

	public int getIdDoacaoOut() {
		return idDoacaoOut;
	}

	public void setIdDoacaoOut(int idDoacaoOut) {
		this.idDoacaoOut = idDoacaoOut;
	}
	
}
