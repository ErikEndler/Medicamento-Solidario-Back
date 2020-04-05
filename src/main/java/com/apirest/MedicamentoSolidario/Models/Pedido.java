package com.apirest.MedicamentoSolidario.Models;

import java.time.LocalDate;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String justificativa;
	private LocalDate data;
	
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

	public Pedido(long id2, String justificativa2, LocalDate data2,Usuario usuario,List<Medicamento> medicamentos2) {
		this.id=id2;
		this.justificativa=justificativa2;
		this.data=data2;
		this.usuario=usuario;
		this.medicamentos = medicamentos2;
		
	}

	public Pedido(String justificativa2, LocalDate data2, Usuario usuario2, List<Medicamento> medicamentos2) {
		this.justificativa=justificativa2;
		this.data=data2;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
	
	
}
