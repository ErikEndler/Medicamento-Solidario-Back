package com.apirest.MedicamentoSolidario.Models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_PEDIDO")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable=false, updatable=false)
	private long id;
	private String justificativa;
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	private Usuario usuario;
		
	@ManyToMany
	@JoinTable(name = "pedido_medicamento",
	joinColumns = @JoinColumn(name="pedido_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="medicamento_id",referencedColumnName = "id")
			)
    private List<Medicamento> medicamentos;
	
	@OneToOne(mappedBy = "pedido")
	private Recebimento recebimento;

	
	public Pedido() {
		
	}

	public Pedido(long id2, String justificativa2, LocalDateTime data2,Usuario usuario,List<Medicamento> medicamentos2) {
		this.id=id2;
		this.justificativa=justificativa2;
		this.setDataCriacao(data2);
		this.usuario=usuario;
		this.medicamentos = medicamentos2;		
	}

	public Pedido(String justificativa2, LocalDateTime data2, Usuario usuario2, List<Medicamento> medicamentos2) {
		this.justificativa=justificativa2;
		this.setDataCriacao(data2);
		this.usuario=usuario2;
		this.medicamentos = medicamentos2;
	}
	
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	
}
