package com.apirest.MedicamentoSolidario.controle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.dto.RecebimentoDTO;
import com.apirest.MedicamentoSolidario.dto.RecebimentoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.MedicamentoRepository;
import com.apirest.MedicamentoSolidario.repository.RecebimentoRepository;

@Service
		//--------------RECEBIMENTO = REMEDIO_OUT-----------------------//
		//--------------SAIDA DO MEDICAMENTO-----------------------//
public class RecebimentoControle {
	@Autowired
	RecebimentoRepository repository;
	PedidoControle pedidocontrol;
	MedicamentoControle medicamentoControl;
	MedicamentoRepository medicamentoRepository;
	UsuarioControle usuarioControle;
	PontoColetaControle pontoColetaControle;
	
	public Recebimento salvar(RecebimentoDTO recebimentoDTO) {
		Optional<Recebimento> ret = verifySave(recebimentoDTO.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + recebimentoDTO.getId());

		} else{
			//instancia pedido e medicamento para alterar o status do medicamento do recebimento
			Recebimento receb = repository.save(transformaSalvar(recebimentoDTO)) ;
			//pega o pedido para buscar a lista de mediamento dele
			List<Medicamento> meds = listarMed(recebimentoDTO.getPedido());
			//percorre a lista de medicamentos para atribuir o recebimento a eles
			//for (Medicamento medicamento : meds) {
				//atribui o recebimento ao medicamento
				//medicamento.setDoacao_out(recebimento);
				// altera o medicamento
				//medicamentoRepository.save(medicamento);
			//}
			return receb ;		
		}
	}
	private Recebimento transformaSalvar(RecebimentoDTO recebimentoDTO) {
		
		return new Recebimento(dataRetirada(), recebimentoDTO.getObs(), voluntario(recebimentoDTO), ponto(recebimentoDTO), pedido(recebimentoDTO));
	}
	private Pedido pedido(RecebimentoDTO recebimentoDTO) {
		return pedidocontrol.listarNormal(recebimentoDTO.getIdPedido()).get();
	}
	private PontoColeta ponto(RecebimentoDTO recebimentoDTO) {
		return pontoColetaControle.listar(recebimentoDTO.getIdPonto()).get();
	}
	private Usuario voluntario(RecebimentoDTO recebimentoDTO) {
		System.out.println("ID VOLUNTARIO: "+recebimentoDTO.getIdVoluntario());
		return usuarioControle.listar(recebimentoDTO.getIdVoluntario()).get();
	}
	private LocalDateTime dataRetirada() {
		 return LocalDateTime.now();
	}
	//função que retorna a lista de medicamentos de um pedido
	private List<Medicamento>listarMed(Pedido pedido){
		return null;		
		  
				//pedido.getMedicamentos();
		
	}
	public Iterable<RecebimentoRespostaDTO> listarTodosNormal() {
		Iterable<Recebimento> listar = repository.findAll();
		List<RecebimentoRespostaDTO> result = new ArrayList<RecebimentoRespostaDTO>();
		for (Recebimento str : listar) {
			result.add(RecebimentoRespostaDTO.transformaEmDTO(str));
		}
		return result;
	}
	
	public Optional<Recebimento> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Recebimento> findById = repository.findById(id);
		return findById;
	}
	
	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}
	
	public Recebimento atualizar(Recebimento recebimento) {
		verifyIfObjectExists(recebimento.getId());
		return repository.save(recebimento);
	}	
	
	//------------------------------//-----------------------
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Recebimento> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<Recebimento> verifySave(long id) {
		Optional<Recebimento> retorno = repository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Recebimento";
		return msg;
	}
}
