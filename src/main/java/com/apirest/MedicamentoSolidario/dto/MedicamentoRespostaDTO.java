package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Recebimento;

public class MedicamentoRespostaDTO {

	private long id;
	private String nome;
	private String principio;
	private String tipoReceita;
	private LocalDateTime dataInsercao;
	private LocalDate dataValidade;
	private String tarja;
	private String tipoArmazenamento;
	private int quantidade;
	private long idDoacaoOut;
	private long idDoacaoIn;
	private long idPonto;

	private MedicamentoRespostaDTO(long id, String nome, String principio, String tipoReceita,
			LocalDateTime dataInsercao, LocalDate dataVencimento, String tarja, String tipoArmazenamento,
			int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.dataInsercao = dataInsercao;
		this.dataValidade = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
	}

	public MedicamentoRespostaDTO(long id, String nome, String principio, String tipoReceita,
			LocalDateTime dataInsercao, LocalDate dataValidade, String tarja, String tipoArmazenamento, Doacao doacao,
			Recebimento recebimento, int quantidade, long idPonto) {
		super();
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.dataInsercao = dataInsercao;
		this.dataValidade = dataValidade;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.setIdDoacaoIn(doacao.getId());
		if (recebimento != null) {
			this.setIdDoacaoIn(recebimento.getId());
		}
		this.quantidade = quantidade;
		this.idPonto = idPonto;
	}

	public static MedicamentoRespostaDTO transformaEmDTO(Medicamento medicamento) {
		return new MedicamentoRespostaDTO(medicamento.getId(), medicamento.getNome(), medicamento.getPrincipio(),
				medicamento.getTipoReceita(), medicamento.getDataInsercao(), medicamento.getDataVencimento(),
				medicamento.getTarja(), medicamento.getTipoArmazenamento(), medicamento.getQuantidade());

	}

	public static MedicamentoRespostaDTO transformaEmDTOList(Medicamento medicamento) {
		return new MedicamentoRespostaDTO(
				medicamento.getId(), 
				medicamento.getNome(),
				medicamento.getPrincipio(),
				medicamento.getTipoReceita(),
				medicamento.getDataInsercao(),
				medicamento.getDataVencimento(),
				medicamento.getTarja(),
				medicamento.getTipoArmazenamento(),
				medicamento.getDoacao_in(),
				medicamento.getDoacao_out(),
				medicamento.getQuantidade(),
				medicamento.getDoacao_in().getPonto().getId()
				);
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

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataVencimento) {
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

	public LocalDateTime getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDateTime dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public long getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}

}
