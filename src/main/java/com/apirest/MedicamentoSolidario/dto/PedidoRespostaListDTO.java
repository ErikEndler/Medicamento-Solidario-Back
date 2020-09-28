package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.Models.Usuario;

public class PedidoRespostaListDTO {
	private long id;
	private String status;
	private String justificativa;
	private LocalDateTime dataCriacao;
	private long usuarioID;
	private String usuarioNome;
	private String usuarioCPF;
	private List<MedicamentoRespostaDTO> medicamentos;
	private long recebimento;
	private long pontoId;
	private String pontoNome;

	public PedidoRespostaListDTO(long id, String status, String justificativa, LocalDateTime dataCriacao,
			Usuario usuario, List<MedicamentoRespostaDTO> medicamentos, Recebimento recebimento, PontoColeta pontoColeta) {
		super();
		this.id = id;
		this.status = status;
		this.justificativa = justificativa;
		this.dataCriacao = dataCriacao;
		this.usuarioID = usuario.getId();
		this.usuarioCPF = usuario.getCpf();
		this.usuarioNome = usuario.getNome();
		this.medicamentos = medicamentos;
		this.pontoId = pontoColeta.getId();
		this.pontoNome = pontoColeta.getNome();
		if (recebimento != null)
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

	public long getPontoId() {
		return pontoId;
	}

	public void setPontoId(long pontoId) {
		this.pontoId = pontoId;
	}

	public String getUsuarioCPF() {
		return usuarioCPF;
	}

	public void setUsuarioCPF(String usuarioCPF) {
		this.usuarioCPF = usuarioCPF;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getPontoNome() {
		return pontoNome;
	}

	public void setPontoNome(String pontoNome) {
		this.pontoNome = pontoNome;
	}

}
