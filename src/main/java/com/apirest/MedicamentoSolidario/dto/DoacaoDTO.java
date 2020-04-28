package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class DoacaoDTO {

	private long id;

	private String obs;
	@NotNull(message = "Falta referencia do doador 'ID'")
	private long idDoador;
	@NotNull(message = "Falta referencia do Responsavel 'ID'")
	private long idVoluntario;
	@NotNull(message = "Falta referencia do Ponto de Coleta 'ID'")
	private long idPonto;
	@NotNull(message = "Deve haver medicamentos na Doação")
	private List<MedicamentoInDTO> medicamentos;

	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataDoacao;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private PontoColeta ponto;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private Usuario doador;
	@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private Usuario voluntario;

	public Doacao transformarParaObjSalvar() {
		return new Doacao(dataDoacao, obs, doador, voluntario, ponto);
	}

	public Doacao transformarParaObjEditar() {
		return new Doacao(id, dataDoacao, obs, doador, voluntario, ponto);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}

	public Usuario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Usuario voluntario) {
		this.voluntario = voluntario;
	}

	public long getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(long idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public PontoColeta getPonto() {
		return ponto;
	}

	public void setPonto(PontoColeta ponto) {
		this.ponto = ponto;
	}

	public long getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}

	public List<MedicamentoInDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoInDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public LocalDateTime getDataDoacao() {
		return dataDoacao;
	}

	public void setDataDoacao(LocalDateTime dataDoacao) {
		this.dataDoacao = dataDoacao;
	}
}
