package com.apirest.MedicamentoSolidario.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.config.DataUtil;
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
	@Autowired
	MedicamentoControle medicamentoControle;
	@Autowired
	DataUtil dataUtil;
	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	PontoColetaControle controlePonto;

	public Doacao salvar(DoacaoDTO doacaoDTO) {
		doacaoDTO.setData(LocalDate.now());
		System.out.println("DATA : " + doacaoDTO.getData());
		doacaoDTO = setIDS(doacaoDTO);
		Doacao doacao = repository.save(doacaoDTO.transformarParaObjSalvar());

		List<MedicamentoInDTO> medicamentoIn = doacaoDTO.getMedicamentos();
		List<Medicamento> medicamentoList = new ArrayList<Medicamento>();
		for (MedicamentoInDTO medicamento : medicamentoIn) {
			// valida o formato da data
			dataUtil.isDateValid(medicamento.getDataValidade());
			// COnverte adata de string para tipo Localdate
			LocalDate data = dataUtil.converterData(medicamento.getDataValidade());
			// seta a data convertida na variavel
			medicamento.setlocalDateDataValidade(data);
			System.out.println("STRING :"+medicamento.getDataValidade());
			System.out.println("LOCALDATE:"+medicamento.getlocalDateDataValidade());
			medicamento.setData(LocalDate.now());
			medicamento = addIdDoacao(medicamento, doacao.getId());
			Medicamento med =medicamentoControle.salvar(medicamento.transformarParaObjSalvar());
			medicamentoList.add(med);
		}
		doacao.setMedicamento(medicamentoList);
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

	public DoacaoDTO setIDS(DoacaoDTO doacaoDTO) {
		doacaoDTO.setDoador(controleUsuario.listar(doacaoDTO.getIdDoador()).get());
		doacaoDTO.setVoluntario(controleUsuario.listar(doacaoDTO.getIdVoluntario()).get());
		doacaoDTO.setPonto(controlePonto.listar(doacaoDTO.getIdPonto()).get());
		return doacaoDTO;
	}

	// ------------
	public MedicamentoInDTO addIdDoacao(MedicamentoInDTO medicamento, long id) {
		medicamento.setFullDoacaoIn(repository.findById(id).get());
		return medicamento;
	}
}
