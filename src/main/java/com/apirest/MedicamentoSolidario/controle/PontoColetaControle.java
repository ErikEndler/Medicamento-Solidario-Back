package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.dto.PontoColetaRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.PontoColetaRepository;

@Service
public class PontoColetaControle {
	@Autowired
	PontoColetaRepository repository;

	public PontoColeta salvar(PontoColeta pontoColeta) {
		Optional<PontoColeta> ret = verifySave(pontoColeta.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pontoColeta.getId());

		} else
			return repository.save(pontoColeta);
	}

	public Iterable<PontoColetaRespostaDTO> listarTodosNormal() {
		Iterable<PontoColeta> listar = repository.findAll();
		List<PontoColetaRespostaDTO> result = new ArrayList<PontoColetaRespostaDTO>();
		for (PontoColeta str : listar) {
			result.add(PontoColetaRespostaDTO.transformaEmDTO(str));
		}
		return result;
	}
	
	public Optional<PontoColeta> listar(long id) {
		verifyIfObjectExists(id);
		Optional<PontoColeta> findById = repository.findById(id);
		return findById;
	}
	
	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}
	
	public PontoColeta atualizar(PontoColeta pontoColeta) {
		verifyIfObjectExists(pontoColeta.getId());
		return repository.save(pontoColeta);
	}	
	
//---------------------------------------------------------------------//
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<PontoColeta> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<PontoColeta> verifySave(long id) {
		Optional<PontoColeta> retorno = repository.findById(id);
		return retorno;
	}

	protected String MenssagemErro() {
		String msg = "Ponto de COleta";
		return msg;
	}

}
