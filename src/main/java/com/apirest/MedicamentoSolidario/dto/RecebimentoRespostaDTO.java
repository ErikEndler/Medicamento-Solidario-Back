package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;

import com.apirest.MedicamentoSolidario.Models.Recebimento;

public class RecebimentoRespostaDTO {
	private long id;
	private LocalDateTime dataretirada;
	private String obs;
	private long idPonto;
	private long idVoluntario;
	private long idPedido;
	private long idUsuario;
	private String cpf;

	public RecebimentoRespostaDTO(long id, LocalDateTime dataRetirada, String obs, long idVoluntario, long idPonto, long idPedido) {
		super();
		this.setId(id);
		this.setDataretirada(dataRetirada);
		this.setObs(obs);
		this.setIdVoluntario(idVoluntario);
		this.setIdPonto(idPonto);
		this.setIdPedido(idPedido);
	}

	public static RecebimentoRespostaDTO transformaEmDTO(Recebimento recebimento) {
		return new RecebimentoRespostaDTO(recebimento.getId(), recebimento.getDataRetirada(), recebimento.getObs(),
				recebimento.getVoluntario().getId(), recebimento.getPonto().getId(), recebimento.getPedido().getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDataretirada() {
		return dataretirada;
	}

	public void setDataretirada(LocalDateTime dataretirada) {
		this.dataretirada = dataretirada;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

}
