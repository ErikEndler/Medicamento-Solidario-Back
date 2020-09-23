package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Recebimento;

public class PedidoRespostaListDTO {
	private long id;
	private String status;
	private String justificativa;
	private LocalDateTime dataCriacao;
	private long usuarioID;
	private List<MedicamentoRespostaDTO> medicamentos;
	private long recebimento;
	
	

	public PedidoRespostaListDTO(long id, String status, String justificativa, LocalDateTime dataCriacao,
			long usuarioID, List<MedicamentoRespostaDTO> medicamentos, Recebimento recebimento) {
		super();
		this.id = id;
		this.status = status;
		this.justificativa = justificativa;
		this.dataCriacao = dataCriacao;
		this.usuarioID = usuarioID;
		this.medicamentos = medicamentos;
		if(recebimento != null)
		this.recebimento = recebimento.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public long getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(long recebimento) {
		this.recebimento = recebimento;
	}

	public List<MedicamentoRespostaDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoRespostaDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

}
