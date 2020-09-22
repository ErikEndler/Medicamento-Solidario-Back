package com.apirest.MedicamentoSolidario.dto;

public class PedidoMedicamentoDTO {
	private int qtd;
	private long medicamentoID;

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public long getMedicamentoID() {
		return medicamentoID;
	}

	public void setMedicamentoID(long medicamentoID) {
		this.medicamentoID = medicamentoID;
	}

}
