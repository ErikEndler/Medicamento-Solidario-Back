package com.apirest.MedicamentoSolidario.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido_medicamento")
public class PedidoMedicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private long id;
	@ManyToOne
	private Pedido pedido;
	@ManyToOne
	private Medicamento medicamento;
	private int qtd;

	public PedidoMedicamento(long id, Pedido pedido, Medicamento medicamento, int qtd) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.medicamento = medicamento;
		this.qtd = qtd;
	}

	public PedidoMedicamento(Pedido pedido, Medicamento medicamento, int qtd) {
		super();
		this.pedido = pedido;
		this.medicamento = medicamento;
		this.qtd = qtd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
