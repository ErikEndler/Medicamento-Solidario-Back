package com.apirest.MedicamentoSolidario.resources;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@CrossOrigin(origins = "*")
public class DoacaoResources {
	@Autowired
	DoacaoControle controle;

	@ApiOperation(value = "Retorna uma lista de Doações")
	@GetMapping("")
	public ResponseEntity<?> listarTodos() {
		return new ResponseEntity<>(controle.listarTodosNormal(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma Doação unica")
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable(value = "id") long id) {
		Optional<Doacao> user = controle.listar(id);
		return new ResponseEntity<>(DoacaoRespostaDTO.transformaEmDTO(user.get()), HttpStatus.OK);
	}

	@ApiOperation(value = "Salva uma Doação")
	@PostMapping("")
	public ResponseEntity<?> salvar(@RequestBody @Valid DoacaoDTO doacaoDTO) {
		// Usuario user = usuarioControle.salvar(usuarioDTO.trsnformaParaObjSalvar());
		Doacao doacao = controle.salvar(doacaoDTO.transformarParaObjSalvar());
		return new ResponseEntity<>(DoacaoRespostaDTO.transformaEmDTO(doacao), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza uma Doação")
	@PutMapping("")
	public ResponseEntity<?> atualizar(@RequestBody @Valid DoacaoDTO dto) {
		Doacao user = controle.atualizar(dto.transformarParaObjEditar());
		return new ResponseEntity<>(DoacaoRespostaDTO.transformaEmDTO(user), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta uma Doação por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		controle.deletarById(id);
		return new ResponseEntity<>("Deletado com sucesso !",HttpStatus.OK);
	}

}
