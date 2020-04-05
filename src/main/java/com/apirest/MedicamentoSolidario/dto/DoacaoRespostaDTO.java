package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Doacao;

public class DoacaoRespostaDTO {
	private long id;
	private LocalDate data;
	private String obs;
	private long idDoador;
	private long idVoluntario;
	private long idPonto;	
	private List<MedicamentoRespostaDTO> medicamentos;
	
	public DoacaoRespostaDTO(long id, LocalDate data, String obs, long idDoador, long idVoluntario, long idPonto) {
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
	public List<MedicamentoRespostaDTO> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<MedicamentoRespostaDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

}
