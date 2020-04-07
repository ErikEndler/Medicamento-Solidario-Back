package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;

public class PedidoRespostaDTO {
	private long id;
	private String justificativa;
	private LocalDate data;
	private long usuarioID;
	private List<Long> medicamentosID;
	private long recebimento;
	
	public PedidoRespostaDTO(long id, String justificativa, LocalDate data, long usuarioID, List<Medicamento> medicamentosID,
			long recebimento) {
		super();
		this.setId(id);
		this.setJustificativa(justificativa);
		this.setData(data);
		this.setUsuarioID(usuarioID);
		this.setMedicamentosID(listar(medicamentosID));
		this.setRecebimento(recebimento);
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
				pedido.getData(), 
				pedido.getUsuario().getId(), 
				pedido.getMedicamentos(), 
				pedido.getRecebimento().getId());
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
	
}
