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

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.controle.PedidoControle;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaListDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/pedido")
@Api(value = "API REST pedido")
@CrossOrigin(origins = "*")
public class PedidoResources {
	@Autowired
	PedidoControle controle;

	@ApiOperation(value = "Retorna uma lista de Pedidos")
	@GetMapping("")
	public ResponseEntity<?> listarTodos() {
		return new ResponseEntity<>(controle.listarTodosNormal(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um Pedido unico")
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable(value = "id") long id) {
		PedidoRespostaListDTO pedido = controle.listar(id);
		// if (pedido.getStatus().equals("aberto") || pedido.getStatus() == null) {
		// return new ResponseEntity<>(PedidoRespostaDTO.respostaPedido(pedido),
		// HttpStatus.OK);
		// }
		// return new ResponseEntity<>(PedidoRespostaDTO.respostaPedidoFull(pedido),
		// HttpStatus.OK);
		return new ResponseEntity<>(pedido, HttpStatus.OK);

	}

	@ApiOperation(value = "Retorna um Pedido unico")
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> listarPorUsuario(@PathVariable(value = "id") long id) {

		return new ResponseEntity<>(controle.listarPorUsuario(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Salva um Pedido")
	@PostMapping("")
	public ResponseEntity<?> salvar(@RequestBody @Valid PedidoDTO pedidoDTO) {
		long pedidoID = controle.salvar(pedidoDTO);
		return new ResponseEntity<>(controle.listar(pedidoID), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Atualiza um Pedido")
	@PutMapping("")
	public ResponseEntity<?> atualizar(@RequestBody @Valid PedidoDTO dto) {
		Pedido resposta = controle.atualizar(dto);
		if (resposta.getRecebimento() != null) {
			return new ResponseEntity<>(PedidoRespostaDTO.respostaPedido(resposta), HttpStatus.OK);
		}
		return new ResponseEntity<>(PedidoRespostaDTO.respostaPedidoFull(resposta), HttpStatus.OK);

	}

	@ApiOperation(value = "Deleta um Pedido por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		controle.deletarById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
