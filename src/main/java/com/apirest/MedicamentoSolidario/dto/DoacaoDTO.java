package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.controle.PontoColetaControle;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DoacaoDTO {

	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	MedicamentoControle controleMedicamento;
	@Autowired
	PontoColetaControle controlePonto;

	private long id;
	private LocalDate data;
	private String obs;
	@NotBlank(message="Falya referencia do doador 'ID'")
	private long idDoador;
	@NotBlank(message="Falya referencia do Responsavel 'ID'")
	private long idVoluntario;
	@NotBlank(message="Falya referencia do Ponto de Coleta 'ID'")
	private long idPonto;
	@JsonIgnore
	private PontoColeta ponto;
	@JsonIgnore
	private Usuario doador;
	@JsonIgnore
	private Usuario voluntario;
	@NotBlank(message="Deve haver medicamentos na Doação")
	private List<MedicamentoInDTO> medicamentos;

	public Doacao transformarParaObjSalvar() {
		this.doador = controleUsuario.listar(id).get();
		this.voluntario = controleUsuario.listar(id).get();
		this.ponto = controlePonto.listar(id).get();
		return new Doacao(data, obs, doador, voluntario, ponto);
	}

	public Doacao transformarParaObjEditar() {
		this.doador = controleUsuario.listar(id).get();
		this.voluntario = controleUsuario.listar(id).get();
		this.ponto = controlePonto.listar(id).get();
		return new Doacao(id, data, obs, doador, voluntario, ponto);
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
}
