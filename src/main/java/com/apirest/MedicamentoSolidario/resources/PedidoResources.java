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

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.controle.PedidoControle;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/pedido")
@Api(value = "API REST pedido")
@CrossOrigin(origins = "*")
public class PedidoResources {
	@Autowired
	PedidoControle controle;
	
	@ApiOperation(value="Retorna uma lista de Pedidos")
	@GetMapping("")
	public Iterable<PedidoRespostaDTO> listarTodos() {
		return controle.listarTodosNormal();
	}
	
	@ApiOperation(value = "Retorna um Pedido unico")
	@GetMapping("/{id}")
	public PedidoRespostaDTO listar(@PathVariable(value="id")long id) {
		Optional<Pedido> pedido = controle.listar(id);
		return PedidoRespostaDTO.transformaEmDTO(pedido.get());
	}
	
	@ApiOperation(value = "Salva um Pedido")
	@PostMapping("")
	public PedidoRespostaDTO salvar(@RequestBody @Valid PedidoDTO pedidoDTO) {
		Pedido pedido = controle.salvar(pedidoDTO.transformarParaObjSalvar());
		return PedidoRespostaDTO.transformaEmDTO(pedido);
		
	}
	
	@ApiOperation(value = "Atualiza um Pedido")
	@PutMapping("")
	public PedidoRespostaDTO atualizar(@RequestBody @Valid PedidoDTO dto) {
		Pedido resposta = controle.atualizar(dto.transformarParaObjEditar());
		return PedidoRespostaDTO.transformaEmDTO(resposta);
	}
	
	@ApiOperation(value = "Deleta um Pedido por Id")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value="id")long id) {
		controle.deletarById(id);
	}

}
