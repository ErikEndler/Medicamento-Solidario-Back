package com.apirest.MedicamentoSolidario.resources;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.controle.DoacaoControle;
import com.apirest.MedicamentoSolidario.dto.DoacaoDTO;
import com.apirest.MedicamentoSolidario.dto.DoacaoRespostaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/doacao")
@Api(value = "API REST doacao")
@CrossOrigin(origins = "*")
public class DoacaoResources {
	@Autowired
	DoacaoControle controle;
	
	@ApiOperation(value="Retorna uma lista de Doações")
	@GetMapping("")
	public Iterable<DoacaoRespostaDTO> listarTodos() {
		return controle.listarTodosNormal();
	}
	
	@ApiOperation(value = "Retorna uma Doação unica")
	@GetMapping("/{id}")
	public DoacaoRespostaDTO listar(@PathVariable(value="id")long id) {	
		Optional<Doacao> user = controle.listar(id);
		return DoacaoRespostaDTO.transformaEmDTO(user.get());
	}	
	
	@ApiOperation(value = "Salva uma Doação")
	@PostMapping("")
	public DoacaoRespostaDTO salvar(@RequestBody @Valid DoacaoDTO doacaoDTO) {
		//Usuario user = usuarioControle.salvar(usuarioDTO.trsnformaParaObjSalvar());
		Doacao doacao = controle.salvar(doacaoDTO.transformarParaObjSalvar());
		return DoacaoRespostaDTO.transformaEmDTO(doacao);
	}
	
	@ApiOperation(value = "Atualiza uma Doação")
	@PutMapping("")
	public DoacaoRespostaDTO atualizar(@RequestBody @Valid DoacaoDTO dto) {
		Doacao user = controle.atualizar(dto.transformarParaObjEditar());
		return DoacaoRespostaDTO.transformaEmDTO(user);
	}


	@ApiOperation(value = "Deleta uma Doação por Id")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value="id")long id) {
		controle.deletarById(id);
	}

}
