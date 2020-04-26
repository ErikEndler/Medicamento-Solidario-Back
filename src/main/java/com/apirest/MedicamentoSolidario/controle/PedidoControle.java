package com.apirest.MedicamentoSolidario.controle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.dto.MedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.PedidoRepository;

@Service
public class PedidoControle {
	@Autowired
	PedidoRepository repository;
	@Autowired
	MedicamentoControle medicamentoControle;
	@Autowired
	UsuarioControle usuarioControle;

	public Pedido salvar(PedidoDTO pedidoDTO) {
		Optional<Pedido> ret = verifySave(pedidoDTO.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pedidoDTO.getId());

		} else {
			pedidoDTO.setDataCriacao(LocalDateTime.now());
			pedidoDTO.setMedicamentosFull(listaMedicamentos(pedidoDTO.getMedicamentos()));
			pedidoDTO.setUsuario(usuarioControle.listar(pedidoDTO.getIdUsauruaio()).get());
			return repository.save(pedidoDTO.transformarParaObjSalvar());
		}
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
	private List<Medicamento> listaMedicamentos(List<MedicamentoDTO> listMedicamDto) {
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		for (MedicamentoDTO medicamentoDTO : listMedicamDto) {
			listaMedicamento.add(medicamentoDTO.TransformarParaObjEditar());
		}
		return listaMedicamento;
	}
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
