package com.apirest.MedicamentoSolidario.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.dto.MedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.MedicamentoRespostaDTO;
import com.apirest.MedicamentoSolidario.repository.MedicamentoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/medicamento")
@Api(value = "API REST medicamento")
@CrossOrigin(origins = "*")
public class MedicamentoResources {

	@Autowired
	MedicamentoControle controle;
	@Autowired
	MedicamentoRepository repository;
//
	@ApiOperation(value = "Retorna medicamentos pelo ponto")
	@GetMapping("/ponto{ponto}")
	public ResponseEntity<?> listarPorPontos(@PathVariable(value = "ponto") long ponto){
		List<MedicamentoRespostaDTO> medList = controle.listarPorPonto(ponto);
		return new ResponseEntity<>(medList,HttpStatus.OK);		
	}
	
	@ApiOperation(value = "Retorna uma lista de Medicamentos")
	@GetMapping("")
	public ResponseEntity<?> listarTodos() {
		return new ResponseEntity<>(controle.listarTodosNormal(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um Medicamento unico")
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable(value = "id") long id) {
		Optional<Medicamento> med = controle.listar(id);
		return new ResponseEntity<>(MedicamentoRespostaDTO.transformaEmDTOList(med.get()), HttpStatus.OK);
	}

//	@ApiOperation(value = "Salva um Medicamento")
//	@PostMapping("")
//	public ResponseEntity<?> salvar(@RequestBody @Valid MedicamentoDTO medicamentoDTO) {
//		Medicamento med = controle.salvar(medicamentoDTO.transformarParaObjSalvar());
//		return new ResponseEntity<>(MedicamentoRespostaDTO.transformaEmDTO(med), HttpStatus.CREATED);
//	}

	@ApiOperation(value = "Atualiza um Medicamento")
	@PutMapping("")
	public ResponseEntity<?> atualizar(@RequestBody @Valid MedicamentoDTO dto) {
		Medicamento resposta = controle.atualizar(dto);
		return new ResponseEntity<>(MedicamentoRespostaDTO.transformaEmDTOList(resposta), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta um Medicamento por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		controle.deletarById(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	

}
