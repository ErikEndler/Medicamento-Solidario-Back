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
	private List<MedicamentoDTO> medicamentos;
	private List<PedidoMedicamentoDTO> listaMedicamentos;

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

	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}

	public List<PedidoMedicamentoDTO> getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(List<PedidoMedicamentoDTO> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

}
