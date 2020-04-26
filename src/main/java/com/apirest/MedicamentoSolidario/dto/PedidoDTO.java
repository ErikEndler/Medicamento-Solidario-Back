package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoDTO {

	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	MedicamentoControle controleMedicamento;

	private long id;
	private String justificativa;
	private LocalDateTime dataCriacao;

	@JsonIgnore
	private Usuario usuario;
	private int idUsauruaio;

	private List<MedicamentoDTO> medicamentos;
	@JsonIgnore
	private List<Medicamento> medicamentosFull;
	@JsonIgnore
	private int idsMedicamentos[];

	@JsonIgnore
	private Recebimento recebimento;
	private int idRecebimento;

	public Pedido transformarParaObjSalvar() {
		return new Pedido(justificativa, dataCriacao, usuario, medicamentosFull);
	}

	public Pedido transformarParaObjEditar() {
		this.usuario = controleUsuario.listar(id).get();

		return new Pedido(id, justificativa, dataCriacao, usuario, medicamentosFull);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public int getIdUsauruaio() {
		return idUsauruaio;
	}

	public void setIdUsauruaio(int idUsauruaio) {
		this.idUsauruaio = idUsauruaio;
	}

	public int[] getIdsMedicamentos() {
		return idsMedicamentos;
	}

	public void setIdsMedicamentos(int[] idMedicamento) {
		this.idsMedicamentos = idMedicamento;
	}

	public int getRecebimentoID() {
		return idRecebimento;
	}

	public void setRecebimentoID(int recebimentoID) {
		this.idRecebimento = recebimentoID;
	}

	public List<Medicamento> getMedicamentosFull() {
		return medicamentosFull;
	}

	public void setMedicamentosFull(List<Medicamento> medicamentos) {
		this.medicamentosFull = medicamentos;
	}

	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}
}
