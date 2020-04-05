package com.apirest.MedicamentoSolidario.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.dto.DoacaoDTO;
import com.apirest.MedicamentoSolidario.dto.DoacaoRespostaDTO;
import com.apirest.MedicamentoSolidario.dto.MedicamentoInDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.DoacaoRepository;

@Service
// -------------------MEDICAMENTO_IN---------------------------//
// -------------------ENTRADA DE MEDICAMENTO-------------------//
public class DoacaoControle {
	@Autowired
	DoacaoRepository repository;
	MedicamentoControle medicamentoControle;

	public Doacao salvar(DoacaoDTO doacaoDTO) {
		doacaoDTO.setData(LocalDate.now());
		Doacao doacao = repository.save(doacaoDTO.transformarParaObjSalvar());
		List<MedicamentoInDTO> medicamentoIn = doacaoDTO.getMedicamentos();
		for (MedicamentoInDTO medicamento : medicamentoIn) {
			addIdDoacao(medicamento, doacao.getId());
			medicamentoControle.salvar(medicamento.transformarParaObjSalvar());
		}
		return doacao;
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

	// ------------------------------//-----------------------
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

	// ------------
	public MedicamentoInDTO addIdDoacao(MedicamentoInDTO medicamento, long id) {
		medicamento.setFullDoacaoIn(repository.findById(id).get());
		return medicamento;
	}
}
