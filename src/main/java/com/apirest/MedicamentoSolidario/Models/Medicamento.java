package com.apirest.MedicamentoSolidario.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MEDICAMENTO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private long id;
	private String nome;
	private String principio;
	private String tipoReceita;
	private LocalDateTime dataInsercao;
	private LocalDate dataVencimento;
	private String tarja;
	private String tipoArmazenamento;
	private int quantidade;
	private boolean status;
	@ManyToOne
	private Doacao doacao_in;

	@OneToMany(mappedBy = "medicamento")
	private List<PedidoMedicamento> pedido_med;

	public Medicamento() {
		super();
	}

	public Medicamento(long id, String nome, String principio, String tipoReceita, LocalDateTime dataInsercao,
			LocalDate dataVencimento, String tarja, String tipoArmazenamento, int quantidade, Doacao fullDoacaoIn) {
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.setDataInsercao(dataInsercao);
		this.dataVencimento = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
		this.doacao_in = fullDoacaoIn;

	}

	public Medicamento(String nome, String principio, String tipoReceita, LocalDateTime dataInsercao,
			LocalDate dataVencimento, String tarja, String tipoArmazenamento, int quantidade, Doacao fullDoacaoIn) {
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.setDataInsercao(dataInsercao);
		this.dataVencimento = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
		this.doacao_in = fullDoacaoIn;
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

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
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

	public Doacao getDoacao_in() {
		return doacao_in;
	}

	public void setDoacao_in(Doacao doacao_in) {
		this.doacao_in = doacao_in;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDateTime dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public List<PedidoMedicamento> getPeido_med() {
		return pedido_med;
	}

	public void setPeido_med(List<PedidoMedicamento> peido_med) {
		this.pedido_med = peido_med;
	}

}
