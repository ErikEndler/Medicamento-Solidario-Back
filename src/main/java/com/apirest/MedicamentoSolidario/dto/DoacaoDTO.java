package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

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
	private Date data;
	private String obs;
	@JsonIgnore
	private Usuario doador;
	private long idDoador;
	@JsonIgnore
	private Usuario voluntario;
	private long idVoluntario;
	@JsonIgnore
	private PontoColeta ponto;
	private long idPonto;
	
	public Doacao transformarParaObjSalvar() {
		this.doador = controleUsuario.listar(id).get();
		this.voluntario = controleUsuario.listar(id).get();
		this.ponto = controlePonto.listar(id).get();
		return new Doacao(id,data,obs,doador,voluntario,ponto);
	}
	public Doacao transformarParaObjEditar() {
		this.doador = controleUsuario.listar(id).get();
		this.voluntario = controleUsuario.listar(id).get();
		this.ponto = controlePonto.listar(id).get();
		return new Doacao(data,obs,doador,voluntario,ponto);
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

}
