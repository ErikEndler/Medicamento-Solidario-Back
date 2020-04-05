package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.apirest.MedicamentoSolidario.Models.Medicamento;

public class MedicamentoRespostaDTO {
	
	private long id;
	private String nome;
	private String principio;
	private String tipoReceita;
	private LocalDate data;
	private Date dataValidade;
	private String tarja;
	private String tipoArmazenamento;
	private int quantidade;
	private long idDoacaoOut;
	private long idDoacaoIn;	
	
	private MedicamentoRespostaDTO(long id, String nome, String principio, String tipoReceita, LocalDate data,
			Date dataVencimento, String tarja, String tipoArmazenamento, int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.data = data;
		this.dataValidade = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
	}
	
	public MedicamentoRespostaDTO(long id, String nome, String principio, String tipoReceita, LocalDate data,
			Date dataVencimento, String tarja, String tipoArmazenamento, long idIn, long idOut, int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.data = data;
		this.dataValidade = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.setIdDoacaoIn(idIn);
		this.setIdDoacaoIn(idOut);
		this.quantidade = quantidade;
	}

	public static MedicamentoRespostaDTO transformaEmDTO(Medicamento medicamento) {
		return new MedicamentoRespostaDTO(medicamento.getId(), 
				medicamento.getNome(),
				medicamento.getPrincipio(),
				medicamento.getTipoReceita(), 
				medicamento.getData(), 
				medicamento.getDataVencimento(), 
				medicamento.getTarja(),
				medicamento.getTipoArmazenamento(), 
				medicamento.getQuantidade());
	}
	public static MedicamentoRespostaDTO transformaEmDTOList(Medicamento medicamento) {
		return new MedicamentoRespostaDTO(medicamento.getId(), 
				medicamento.getNome(),
				medicamento.getPrincipio(),
				medicamento.getTipoReceita(), 
				medicamento.getData(), 
				medicamento.getDataVencimento(), 
				medicamento.getTarja(),
				medicamento.getTipoArmazenamento(),
				medicamento.getDoacao_in().getId(),
				medicamento.getDoacao_out().getId(),
				medicamento.getQuantidade());				
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Date getDataVencimento() {
		return dataValidade;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataValidade = dataVencimento;
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

	public long getIdDoacaoOut() {
		return idDoacaoOut;
	}

	public void setIdDoacaoOut(long idDoacaoOut) {
		this.idDoacaoOut = idDoacaoOut;
	}

	public long getIdDoacaoIn() {
		return idDoacaoIn;
	}

	public void setIdDoacaoIn(long idDoacaoIn) {
		this.idDoacaoIn = idDoacaoIn;
	}
	
}
