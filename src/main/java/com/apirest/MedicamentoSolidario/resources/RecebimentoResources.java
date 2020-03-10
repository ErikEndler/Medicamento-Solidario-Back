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

import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.controle.RecebimentoControle;
import com.apirest.MedicamentoSolidario.dto.RecebimentoDTO;
import com.apirest.MedicamentoSolidario.dto.RecebimentoRespostaDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/recebimento")
@Api(value = "API REST doacao")
@CrossOrigin(origins = "*")
public class RecebimentoResources {
	@Autowired
	RecebimentoControle controle;
	
	@ApiOperation(value="Retorna uma lista de Recebimentos")
	@GetMapping("")
	public Iterable<RecebimentoRespostaDTO> listarTodos() {
		return controle.listarTodosNormal();
	}
	
	@ApiOperation(value = "Retorna um Recebimento unico")
	@GetMapping("/{id}")
	public RecebimentoRespostaDTO listar(@PathVariable(value="id")long id) {	
		Optional<Recebimento> user = controle.listar(id);
		return RecebimentoRespostaDTO.transformaEmDTO(user.get());
	}	
	
	@ApiOperation(value = "Salva um Recebimento")
	@PostMapping("")
	public RecebimentoRespostaDTO salvar(@RequestBody @Valid RecebimentoDTO recebimentoDTO) {
		Recebimento recebimento = controle.salvar(recebimentoDTO.transformarParaObjSalvar());
		return RecebimentoRespostaDTO.transformaEmDTO(recebimento);
	}
	
	@ApiOperation(value = "Atualiza um Recebimento")
	@PutMapping("")
	public RecebimentoRespostaDTO atualizar(@RequestBody @Valid RecebimentoDTO dto) {
		Recebimento recebimento = controle.atualizar(dto.transformarParaObjEditar());
		return RecebimentoRespostaDTO.transformaEmDTO(recebimento);
	}


	@ApiOperation(value = "Deleta um Recebimento por Id")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value="id")long id) {
		controle.deletarById(id);
	}

}
