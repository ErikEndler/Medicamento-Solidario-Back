package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.PedidoMedicamento;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;

public class PedidoRespostaDTO {
	private long id;
	private String status;
	private String justificativa;
	private LocalDateTime dataCriacao;
	private long usuarioID;
	private List<PedidoMedicamentoDTO> pedidoMedicamentos;
	private long recebimento;

	public PedidoRespostaDTO(long id, String status, String justificativa, LocalDateTime dataCriacao, long usuarioID,
			List<PedidoMedicamento> pedidoMedicamentos, long recebimento) {
		super();
		this.id = id;
		this.status = status;
		this.justificativa = justificativa;
		this.dataCriacao = dataCriacao;
		this.usuarioID = usuarioID;
		this.pedidoMedicamentos = transformaLista(pedidoMedicamentos);
		this.recebimento = recebimento;
	}

	public PedidoRespostaDTO(long id, String status, String justificativa, LocalDateTime dataCriacao, long usuarioID,
			List<PedidoMedicamento> pedidoMedicamentos) {
		super();
		this.id = id;
		this.status = status;
		this.justificativa = justificativa;
		this.dataCriacao = dataCriacao;
		this.usuarioID = usuarioID;
		this.pedidoMedicamentos = transformaLista(pedidoMedicamentos);
	}

	private List<PedidoMedicamentoDTO> transformaLista(List<PedidoMedicamento> listPedidoMedicamentos) {
		List<PedidoMedicamentoDTO> listaMedicamentosDTO = new ArrayList<>();
		
		for (PedidoMedicamento pedidoMedicamento : listPedidoMedicamentos) {
			listaMedicamentosDTO.add(
					new PedidoMedicamentoDTO(pedidoMedicamento.getQtd(), pedidoMedicamento.getMedicamento().getId()));
		}
		return listaMedicamentosDTO;
	}

	public static Object respostaPedido(Pedido pedido) {
		return new PedidoRespostaDTO(pedido.getId(), pedido.getStatus(), pedido.getJustificativa(),
				pedido.getDataCriacao(), pedido.getUsuario().getId(), pedido.getPedido_med());
	}

	public static Object respostaPedidoFull(Pedido pedido) {
		return new PedidoRespostaDTO(pedido.getId(), pedido.getStatus(), pedido.getJustificativa(),
				pedido.getDataCriacao(), pedido.getUsuario().getId(), pedido.getPedido_med(),pedido.getRecebimento().getId());
	}

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

	public List<PedidoMedicamentoDTO> getMedicamentos() {
		return pedidoMedicamentos;
	}

	public void setMedicamentos(List<PedidoMedicamentoDTO> medicamentos) {
		this.pedidoMedicamentos = medicamentos;
	}

	public long getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(long recebimento) {
		this.recebimento = recebimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
