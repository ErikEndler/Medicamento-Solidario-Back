package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.dto.MedicamentoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.MedicamentoRepository;

@Service
public class MedicamentoControle {

	@Autowired
	MedicamentoRepository repository;

	public Medicamento salvar(Medicamento medicamento) {
		Optional<Medicamento> ret = verifySave(medicamento.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + medicamento.getId());

		} else
			//medicamento.setDoacao_in(null);
			medicamento.setDoacao_out(null);
			medicamento.setStatus(false);
			return repository.save(medicamento);
	}

	public Iterable<MedicamentoRespostaDTO> listarTodosNormal() {
		Iterable<Medicamento> listar = repository.findAll();
		List<MedicamentoRespostaDTO> result = new ArrayList<MedicamentoRespostaDTO>();
		for (Medicamento str : listar) {
			result.add(MedicamentoRespostaDTO.transformaEmDTOList(str));
		}
		return result;
	}
	
	public Optional<Medicamento> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Medicamento> findById = repository.findById(id);
		return findById;
	}
	
	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}
	
	public Medicamento atualizar(Medicamento medicamento) {
		verifyIfObjectExists(medicamento.getId());
		return repository.save(medicamento);
	}
	
	
	
//---------------------------------------------------------------------//
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Medicamento> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<Medicamento> verifySave(long id) {
		Optional<Medicamento> retorno = repository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Medicamento";
		return msg;
	}

}
