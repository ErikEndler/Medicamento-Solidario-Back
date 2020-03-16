package com.apirest.MedicamentoSolidario.resources;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.apirest.MedicamentoSolidario.dto.UsuarioDTO;
import com.apirest.MedicamentoSolidario.dto.UsuarioRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/usuario")
@Api(value = "API REST Usuario")
@CrossOrigin(origins = "*")
public class UsuarioResources {

	@Autowired
	UsuarioControle usuarioControle;

	@ApiOperation(value = "Retorna uma lista de Usuarios")
	@GetMapping("")
	public Iterable<UsuarioRespostaDTO> listarTodos() {
		return usuarioControle.listarTodosNormal();
	}

	@ApiOperation(value = "Retorna um Usuario unico")
	@GetMapping("/{id}")
	public UsuarioRespostaDTO listar(@PathVariable(value = "id") long id) {

		Optional<Usuario> user = usuarioControle.listar(id);
		if (user == null) {
			throw new ResourceNotFoundException("Usuario n encontrado para ID : "+id);
		}
		return UsuarioRespostaDTO.transformaEmDTO(user.get());
	}

	@ApiOperation(value = "Salva um Usuario")
	@PostMapping("")
	public ResponseEntity<UsuarioRespostaDTO> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO, BindingResult result) {
		
		Usuario user = usuarioControle.salvar2(usuarioDTO);
		return new ResponseEntity<UsuarioRespostaDTO>(UsuarioRespostaDTO.transformaEmDTO(user), HttpStatus.CREATED) ;
	}

	@ApiOperation(value = "Atualiza um Usuario")
	@PutMapping("")
	public UsuarioRespostaDTO atualizar(@RequestBody @Valid UsuarioDTO dto) {
		Usuario user = usuarioControle.atualizar(dto.trsnformaParaObjEditar());
		return UsuarioRespostaDTO.transformaEmDTO(user);
	}

	@ApiOperation(value = "Deleta um Usuario por Id")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") long id) {
		usuarioControle.deletarById(id);
	}
}
