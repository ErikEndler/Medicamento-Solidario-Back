package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.controle.PedidoControle;
import com.apirest.MedicamentoSolidario.controle.PontoColetaControle;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RecebimentoDTO {
	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	MedicamentoControle controleMedicamento;
	@Autowired
	PontoColetaControle controlePonto;
	@Autowired
	PedidoControle controlePedido;
	private long id;
	private LocalDate data;
	private String obs;	
	private long idVoluntario;	
	private long idPonto;	
	private long idPedido;
	@JsonIgnore
	private Usuario voluntario;
	@JsonIgnore
	private PontoColeta ponto;
	@JsonIgnore
	private Pedido pedido;
	
	public Recebimento transformarParaObjSalvar() {
		this.voluntario = controleUsuario.listar(idVoluntario).get();
		this.ponto = controlePonto.listar(idPonto).get();
		this.pedido = controlePedido.listar(idPedido).get();
	
		return new Recebimento(id,data,obs,voluntario,ponto,pedido);
	}
	public Recebimento transformarParaObjEditar() {
		this.pedido = controlePedido.listar(id).get();
		this.voluntario = controleUsuario.listar(id).get();
		this.ponto = controlePonto.listar(id).get();
		return new Recebimento(data,obs,voluntario,ponto,pedido);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Usuario getVoluntario() {
		return voluntario;
	}
	public void setVoluntario(Usuario voluntario) {
		this.voluntario = voluntario;
	}
	public long getIdVoluntario() {
		return idVoluntario;
	}
	public void setIdVoluntario(long idVoluntario) {
		this.idVoluntario = idVoluntario;
	}
	public PontoColeta getPonto() {
		return ponto;
	}
	public void setPonto(PontoColeta ponto) {
		this.ponto = ponto;
	}
	public long getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	
	

}
