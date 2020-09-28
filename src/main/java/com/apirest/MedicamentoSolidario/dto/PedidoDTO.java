package com.apirest.MedicamentoSolidario.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;

public class PedidoDTO {

	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	MedicamentoControle controleMedicamento;

	private long id;
	private String justificativa;
	private long idUsuario;
	private List<PedidoMedicamentoDTO> listaMedicamentos;
	private long idPonto;

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

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<PedidoMedicamentoDTO> getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(List<PedidoMedicamentoDTO> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

	public long getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}

}
