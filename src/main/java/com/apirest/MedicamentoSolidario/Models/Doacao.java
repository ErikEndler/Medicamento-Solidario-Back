package com.apirest.MedicamentoSolidario.Models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_DOACAO")
//quando o medicamento é doado para o posto de coleta
// MEDICAMENTO_IN
public class Doacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable=false, updatable=false)
	private long id;
	private LocalDate data;
	private String obs;
	
	@ManyToOne
	private Usuario doador;
	
	@ManyToOne
	private Usuario voluntario;
	
	@ManyToOne
	private PontoColeta ponto;
	
	@OneToMany(mappedBy = "doacao_in")
	private List<Medicamento> medicamento;
	
	public Doacao() {
		super();
	}

	public Doacao(long id2, LocalDate data2, String obs2, Usuario doador2, Usuario voluntario2, PontoColeta ponto2) {
		this.id=id2;
		this.data=data2;
		this.obs=obs2;
		this.doador=doador2;
		this.voluntario=voluntario2;
		this.ponto=ponto2;
	}

	public Doacao(LocalDate data2, String obs2, Usuario doador2, Usuario voluntario2, PontoColeta ponto2) {
		this.data=data2;
		this.obs=obs2;
		this.doador=doador2;
		this.voluntario=voluntario2;
		this.ponto=ponto2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Usuario getDoador() {
		return doador;
	}

	public void setDoador(Usuario doador) {
		this.doador = doador;
	}

	public Usuario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Usuario voluntario) {
		this.voluntario = voluntario;
	}

	public PontoColeta getPonto() {
		return ponto;
	}

	public void setPonto(PontoColeta ponto) {
		this.ponto = ponto;
	}

	public List<Medicamento> getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(List<Medicamento> medicamento) {
		this.medicamento = medicamento;
	}
	
	

}
