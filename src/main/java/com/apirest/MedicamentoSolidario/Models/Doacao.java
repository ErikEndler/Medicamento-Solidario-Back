package com.apirest.MedicamentoSolidario.Models;

import java.sql.Date;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date data;
	private String obs;
	
	@ManyToOne
	private Usuario doador;
	
	@ManyToOne
	private Usuario voluntario;
	
	@ManyToOne
	private PontoColeta ponto;
	
	@OneToMany(mappedBy = "doacao_in")
	private List<Medicamento> medicamento;

	public Doacao(long id2, Date data2, String obs2, Usuario doador2, Usuario voluntario2, PontoColeta ponto2) {
		this.id=id2;
		this.data=data2;
		this.obs=obs2;
		this.doador=doador2;
		this.voluntario=voluntario2;
		this.ponto=ponto2;
	}

	public Doacao(Date data2, String obs2, Usuario doador2, Usuario voluntario2, PontoColeta ponto2) {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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
