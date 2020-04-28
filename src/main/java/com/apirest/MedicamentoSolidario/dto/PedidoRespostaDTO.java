package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;

public class PedidoRespostaDTO {
	private long id;
	private String justificativa;
	private LocalDateTime dataCriacao;
	private long usuarioID;
	private List<Long> medicamentosID;
	private long recebimento;
	
	public PedidoRespostaDTO(long id, String justificativa, LocalDateTime dataCriacao, long usuarioID, List<Medicamento> medicamentosID,
			long recebimento) {
		super();
		this.setId(id);
		this.setJustificativa(justificativa);
		this.setDataCriacao(dataCriacao);
		this.setUsuarioID(usuarioID);
		this.setMedicamentosID(listar(medicamentosID));
		this.setRecebimento(recebimento);
	}
	public PedidoRespostaDTO(long id, String justificativa, LocalDateTime dataCriacao, long usuarioID, List<Medicamento> medicamentosID
			) {
		super();
		this.setId(id);
		this.setJustificativa(justificativa);
		this.setDataCriacao(dataCriacao);
		this.setUsuarioID(usuarioID);
		this.setMedicamentosID(listar(medicamentosID));
		
	}
	
	private List<Long> listar(List<Medicamento> medicamentosID2) {
		List<Long> lista =  new ArrayList<Long>();
		for (Medicamento medicamento : medicamentosID2) {
			lista.add(medicamento.getId());
		}
		return lista;				
	}

	public static PedidoRespostaDTO transformaEmDTO(Pedido pedido) {
		return new PedidoRespostaDTO(
				pedido.getId(), 
				pedido.getJustificativa(), 
				pedido.getDataCriacao(), 
				pedido.getUsuario().getId(), 
				pedido.getMedicamentos(),
				pedido.getRecebimento().getId());
	}
	public static PedidoRespostaDTO transformaEmDTOSave(Pedido pedido) {
		return new PedidoRespostaDTO(
				pedido.getId(), 
				pedido.getJustificativa(), 
				pedido.getDataCriacao(), 
				pedido.getUsuario().getId(), 
				pedido.getMedicamentos());				
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


	public long getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(long recebimento) {
		this.recebimento = recebimento;
	}

	public long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(long usuarioID2) {
		this.usuarioID = usuarioID2;
	}

	public List<Long> getMedicamentosID() {
		return medicamentosID;
	}

	public void setMedicamentosID(List<Long> medicamentosID) {
		this.medicamentosID = medicamentosID;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
