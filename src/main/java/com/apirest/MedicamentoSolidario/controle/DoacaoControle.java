package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.dto.DoacaoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.DoacaoRepository;

@Service
		//-------------------MEDICAMENTO_IN---------------------------//
		//-------------------ENTRADA DE MEDICAMENTO-------------------//
public class DoacaoControle {
	@Autowired
	DoacaoRepository repository;
	
	public Doacao salvar(Doacao doacao) {
		Optional<Doacao> ret = verifySave(doacao.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + doacao.getId());

		} else
			//doacao.setData(LocalDate.now());
			return repository.save(doacao);		
	}
	public Iterable<DoacaoRespostaDTO> listarTodosNormal() {
		Iterable<Doacao> listar = repository.findAll();
		List<DoacaoRespostaDTO> result = new ArrayList<DoacaoRespostaDTO>();
		for (Doacao str : listar) {
			result.add(DoacaoRespostaDTO.transformaEmDTO(str));
		}
		return result;
	}
	
	public Optional<Doacao> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Doacao> findById = repository.findById(id);
		return findById;
	}
	
	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}
	
	public Doacao atualizar(Doacao doacao) {
		verifyIfObjectExists(doacao.getId());
		return repository.save(doacao);
	}	
	
	//------------------------------//-----------------------
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Doacao> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<Doacao> verifySave(long id) {
		Optional<Doacao> retorno = repository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Doação";
		return msg;
	}
}
