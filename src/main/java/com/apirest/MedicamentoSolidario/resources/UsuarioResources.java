package com.apirest.MedicamentoSolidario.resources;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.apirest.MedicamentoSolidario.dto.UsuarioDTO;
import com.apirest.MedicamentoSolidario.dto.UsuarioRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/usuario")
@Api(value = "API REST Usuario")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioResources {

	@Autowired
	UsuarioControle usuarioControle;

	@ApiOperation(value = "Retorna uma lista de Usuarios")
	@GetMapping("")
	public ResponseEntity<?> listarTodos() {
		return new ResponseEntity<> (usuarioControle.listarTodosNormal(),HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um Usuario unico")
	@GetMapping("/{id}")
	public ResponseEntity<?>  listar(@PathVariable(value = "id") long id) {
		Optional<Usuario> user = usuarioControle.listar(id);
		if (user == null) {
			throw new ResourceNotFoundException("Usuario n encontrado para ID : "+id);
		}
		return new ResponseEntity<>(UsuarioRespostaDTO.transformaEmDTO(user.get()),HttpStatus.OK);
	}

	@ApiOperation(value = "Salva um Usuario")
	@PostMapping("")
	public ResponseEntity<?> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {		
		Usuario user = usuarioControle.salvar2(usuarioDTO);
		return new ResponseEntity<>(UsuarioRespostaDTO.transformaEmDTO(user), HttpStatus.CREATED) ;
	}

	@ApiOperation(value = "Atualiza um Usuario")
	@PutMapping("")
	public ResponseEntity<?> atualizar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		Usuario user = usuarioControle.atualizar(usuarioDTO);
		return new ResponseEntity<> (UsuarioRespostaDTO.transformaEmDTO(user),HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta um Usuario por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		usuarioControle.deletarById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
