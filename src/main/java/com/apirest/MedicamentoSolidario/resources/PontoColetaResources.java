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

import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.controle.PontoColetaControle;
import com.apirest.MedicamentoSolidario.dto.PontoColetaDTO;
import com.apirest.MedicamentoSolidario.dto.PontoColetaRespostaDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/pontoColeta")
@Api(value = "API REST PontoCOleta")
//@CrossOrigin(origins = "*")
public class PontoColetaResources {
	@Autowired
	PontoColetaControle controle;

	@ApiOperation(value = "Retorna uma lista de Pontos de Coleta")
	@GetMapping("")
	public ResponseEntity<?> listarTodos() {
		return new ResponseEntity<>(controle.listarTodosNormal(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um Ponto de Coleta unico")
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable(value = "id") long id) {
		Optional<PontoColeta> med = controle.listar(id);
		return new ResponseEntity<>(PontoColetaRespostaDTO.transformaEmDTO(med.get()), HttpStatus.OK);
	}

	@ApiOperation(value = "Salva um Ponto de Coleta")
	@PostMapping("")
	public ResponseEntity<?> salvar(@RequestBody @Valid PontoColetaDTO pontoCOletaDTO) {
		PontoColeta med = controle.salvar(pontoCOletaDTO.transformarParaObjSalvar());
		return new ResponseEntity<>(PontoColetaRespostaDTO.transformaEmDTO(med), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza um Ponto de Coleta")
	@PutMapping("")
	public ResponseEntity<?> atualizar(@RequestBody @Valid PontoColetaDTO pontoColetaDTO) {
		PontoColeta resposta = controle.atualizar(pontoColetaDTO.transformarParaObjEditar());
		return new ResponseEntity<>(PontoColetaRespostaDTO.transformaEmDTO(resposta), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta um Ponto de Coleta por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		controle.deletarById(id);
		return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
	}

}
