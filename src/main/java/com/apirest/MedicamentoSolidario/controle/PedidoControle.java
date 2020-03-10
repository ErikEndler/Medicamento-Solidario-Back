package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.PedidoRepository;

@Service
public class PedidoControle {
	@Autowired
	PedidoRepository repository;

	public Pedido salvar(Pedido pedido) {
		Optional<Pedido> ret = verifySave(pedido.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pedido.getId());

		} else
			return repository.save(pedido);
	}

	public Iterable<PedidoRespostaDTO> listarTodosNormal() {
		Iterable<Pedido> listar = repository.findAll();
		List<PedidoRespostaDTO> result = new ArrayList<PedidoRespostaDTO>();
		for (Pedido str : listar) {
			result.add(PedidoRespostaDTO.transformaEmDTO(str));
		}
		return result;
	}
	
	public Optional<Pedido> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Pedido> findById = repository.findById(id);
		return findById;
	}
	
	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}
	
	public Pedido atualizar(Pedido pedido) {
		verifyIfObjectExists(pedido.getId());
		return repository.save(pedido);
	}
	
	
	
//---------------------------------------------------------------------//
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Pedido> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<Pedido> verifySave(long id) {
		Optional<Pedido> retorno = repository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Medicamento";
		return msg;
	}


}
