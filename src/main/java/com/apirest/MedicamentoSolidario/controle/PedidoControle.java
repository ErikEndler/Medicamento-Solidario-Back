package com.apirest.MedicamentoSolidario.controle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.PedidoMedicamento;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.dto.MedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoMedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.PedidoMedicamentoRepository;
import com.apirest.MedicamentoSolidario.repository.PedidoRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Service
public class PedidoControle {
	@Autowired
	PedidoRepository repository;
	@Autowired
	MedicamentoControle medicamentoControle;
	@Autowired
	UsuarioControle usuarioControle;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PedidoMedicamentoRepository pedidomeMedicamentoRepository;

	public Pedido salvar(PedidoDTO pedidoDTO) {
		Optional<Pedido> ret = verifySave(pedidoDTO.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pedidoDTO.getId());

		} else {
			Pedido pedido = repository.save(transformaSalvarPedido(pedidoDTO));
			System.out.println("Pedido salvo");
			salvarPed_med(pedidoDTO);
			System.out.println("Pedido_medicamento salvo");
			return pedido;
		}
	}

	private void salvarPed_med(PedidoDTO dto) {
		for (PedidoMedicamentoDTO item : dto.getListaMedicamentos()) {
			pedidomeMedicamentoRepository.save(new PedidoMedicamento(repository.findById(dto.getId()).get(),
					medicamentoControle.listar(item.getMedicamentoID()).get(), item.getQtd()));
		}
	}

	private Pedido transformaSalvarPedido(PedidoDTO dto) {
		return new Pedido(dto.getJustificativa(), dataCriacao(), getUsuario(dto));
	}

	private Usuario getUsuario(PedidoDTO dto) {
		return usuarioControle.listar(dto.getIdUsuario()).get();
	}

	private LocalDateTime dataCriacao() {
		return LocalDateTime.now();
	}

	public Iterable<PedidoRespostaDTO> listarTodosNormal() {
		Iterable<Pedido> listar = repository.findAll();
		List<PedidoRespostaDTO> result = new ArrayList<PedidoRespostaDTO>();
		for (Pedido str : listar) {
			if (str.getRecebimento() != null) {
				result.add(PedidoRespostaDTO.transformaEmDTO(str));
			} else {
				result.add(PedidoRespostaDTO.transformaEmDTOSave(str));
			}
		}
		return result;
	}

	public List<PedidoRespostaDTO> listarPorUsuario(long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		List<Pedido> pedidos = repository.findByUsuario(usuario);
		List<PedidoRespostaDTO> result = new ArrayList<PedidoRespostaDTO>();
		for (Pedido str : pedidos) {
			if (str.getRecebimento() != null) {
				result.add(PedidoRespostaDTO.transformaEmDTO(str));
			} else {
				result.add(PedidoRespostaDTO.transformaEmDTOSave(str));
			}
		}
		return result;
	}

	public Optional<Pedido> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Pedido> findById = repository.findById(id);
		return findById;
	}

	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
	}

	public Pedido atualizar(Pedido pedido) {
		verifyIfObjectExists(pedido.getId());
		return repository.save(pedido);
	}

//---------------------------------------------------------------------//

	// transforma a lista de medicamentos no formato de DTO para formato de
	// Medicamento
	private List<Medicamento> listaMedicamentos(List<MedicamentoDTO> listMedicamDto) {
		List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
		for (MedicamentoDTO medicamentoDTO : listMedicamDto) {
			listaMedicamento.add(medicamentoDTO.TransformarParaObjEditar());
		}
		return listaMedicamento;
	}

	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Pedido> retorno = repository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private Optional<Pedido> verifySave(long id) {
		Optional<Pedido> retorno = repository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Pedido";
		return msg;
	}

}
