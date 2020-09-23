package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MedicamentoDTO {

	private long id;
	private String nome;
	private String principio;
	private String tipoReceita;
	private String tarja;
	private String tipoArmazenamento;
	private int quantidade;
	private long idDoacaoIn;
	private String dataValidade;

	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private LocalDate dataValidadeLocalDate;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataInsercao;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private Doacao fullDoacaoIn;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private Recebimento fullDoacaoOut;

	// edita sem interagir com as doaçoes
	public Medicamento TransformarParaObjEditar() {
		return new Medicamento(id, nome, principio, tipoReceita, dataInsercao, dataValidadeLocalDate, tarja,
				tipoArmazenamento, quantidade, fullDoacaoIn);
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

	public LocalDateTime getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDateTime dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public LocalDate getDataValidadeLocalDate() {
		return dataValidadeLocalDate;
	}

	public void setDataValidadeLocalDate(LocalDate dataVencimento) {
		this.dataValidadeLocalDate = dataVencimento;
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

	public long getIdDoacaoIn() {
		return idDoacaoIn;
	}

	public void setIdDoacaoIn(long idDoacaoIn) {
		this.idDoacaoIn = idDoacaoIn;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

}
