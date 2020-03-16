package com.apirest.MedicamentoSolidario.Models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_MEDICAMENTO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String principio;
	private String tipoReceita;
	@NotBlank
	private Date data;
	@NotBlank
	private Date dataVencimento;
	private String tarja;
	@NotBlank
	private String tipoArmazenamento;
	@NotBlank
	private int quantidade;
	private boolean status;	
	@ManyToOne
	private Doacao doacao_in;	
	@ManyToOne
	private Recebimento doacao_out;	
	@ManyToMany(mappedBy = "medicamentos")
	private List<Pedido> pedidos;	

	public Medicamento() {
		super();
	}
	
	public Medicamento(long id, String nome, String principio, String tipoReceita, Date data, Date dataVencimento,
			String tarja, String tipoArmazenamento, int quantidade,int idDoacaoin) {
		this.id = id;
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.data = data;
		this.dataVencimento = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
	}
	
	public Medicamento( String nome, String principio, String tipoReceita, Date data, Date dataVencimento,
			String tarja, String tipoArmazenamento, int quantidade) {
		this.nome = nome;
		this.principio = principio;
		this.tipoReceita = tipoReceita;
		this.data = data;
		this.dataVencimento = dataVencimento;
		this.tarja = tarja;
		this.tipoArmazenamento = tipoArmazenamento;
		this.quantidade = quantidade;
	}
	
	public List<?> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
	public Doacao getDoacao_in() {
		return doacao_in;
	}
	public void setDoacao_in(Doacao doacao_in) {
		this.doacao_in = doacao_in;
	}
	public Recebimento getDoacao_out() {
		return doacao_out;
	}
	public void setDoacao_out(Recebimento doacao_out) {
		this.doacao_out = doacao_out;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
