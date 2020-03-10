package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

import com.apirest.MedicamentoSolidario.Models.Doacao;

public class DoacaoRespostaDTO {
	private long id;
	private Date data;
	private String obs;
	private long idDoador;
	private long idVoluntario;
	private long idPonto;	
	
	public DoacaoRespostaDTO(long id, Date data, String obs, long idDoador, long idVoluntario, long idPonto) {
		super();
		this.id = id;
		this.data = data;
		this.obs = obs;
		this.idDoador = idDoador;
		this.idVoluntario = idVoluntario;
		this.idPonto = idPonto;
	}
	public static DoacaoRespostaDTO transformaEmDTO(Doacao doacao) {
		return new DoacaoRespostaDTO(
				doacao.getId(), 
				doacao.getData(),
				doacao.getObs(), 
				doacao.getDoador().getId(), 
				doacao.getVoluntario().getId(), 
				doacao.getPonto().getId());
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
	public long getIdDoador() {
		return idDoador;
	}
	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}
	public long getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}
	public long getIdVoluntario() {
		return idVoluntario;
	}
	public void setIdVoluntario(long idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

}
