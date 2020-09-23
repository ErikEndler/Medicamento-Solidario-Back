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
import com.apirest.MedicamentoSolidario.dto.MedicamentoRespostaDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoMedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoRespostaListDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.MedicamentoRepository;
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
	@Autowired
	MedicamentoRepository medicamentoRepository;

	// --------------METODO SALVAR--------------
	public PedidoRespostaDTO salvar(PedidoDTO pedidoDTO) {
		Optional<Pedido> ret = verifySave(pedidoDTO.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pedidoDTO.getId());

		} else {
			// Salva pedido
			Pedido pedido = repository.save(transformaSalvarPedido(pedidoDTO));
			System.out.println("Pedido salvo");
			// Cahama metodo para salvar relaça ode medicamentos com pedido
			salvarPed_med(pedidoDTO, pedido);
			System.out.println("Pedido_medicamento salvo");
			//pedido = repository.findById(pedido.getId()).get();
			
			
			return transformaEmRespostaSemRecebimento(repository.findById(pedido.getId()).get());
		}
	}

	// -----------------METODO ATUALIZAR------------------
	public Pedido atualizar(PedidoDTO dto) {
		verifyIfObjectExists(dto.getId());
		transformaEditarPedido(dto);
		return repository.save(transformaEditarPedido(dto));
	}

	private Pedido transformaEditarPedido(PedidoDTO dto) {
		return new Pedido(dto.getId(), dto.getJustificativa(), getUsuario(dto));
	}

	private void salvarPed_med(PedidoDTO dto, Pedido pedido) {
		verifyIfObjectExists(pedido.getId());
		for (PedidoMedicamentoDTO item : dto.getListaMedicamentos()) {
			pedidomeMedicamentoRepository.save(new PedidoMedicamento(repository.findById(pedido.getId()).get(),
					medicamentoControle.listar(item.getMedicamentoID()).get(), item.getQtd()));
			Medicamento medicamento = medicamentoControle.listar(item.getMedicamentoID()).get();
			medicamento.setQuantidade(medicamento.getQuantidade()-item.getQtd());
			medicamentoRepository.save(medicamento);
		}
	}

	private Pedido transformaSalvarPedido(PedidoDTO dto) {
		return new Pedido(dto.getJustificativa(), dataCriacao(), getUsuario(dto), "aberto");
	}

	private Usuario getUsuario(PedidoDTO dto) {
		return usuarioControle.listar(dto.getIdUsuario()).get();
	}

	private LocalDateTime dataCriacao() {
		return LocalDateTime.now();
	}

	// ----------------METODO LISTAR TODOS-------------------
	public Iterable<PedidoRespostaDTO> listarTodosNormal() {
		Iterable<Pedido> listaPedidos = repository.findAll();
		List<PedidoRespostaDTO> result = new ArrayList<PedidoRespostaDTO>();
		for (Pedido pedido : listaPedidos) {
			if (pedido.getRecebimento() != null) {
				result.add(transformaEmResposta(pedido));
			} else {
				result.add(transformaEmRespostaSemRecebimento(pedido));
			}
		}
		return result;
	}

	private PedidoRespostaDTO transformaEmRespostaSemRecebimento(Pedido pedido) {
		System.out.println("lISTA BUGADA: "+pedido.getPedido_med().toString());
		return new PedidoRespostaDTO(pedido.getId(), pedido.getStatus(), pedido.getJustificativa(),
				pedido.getDataCriacao(), pedido.getUsuario().getId(), pedido.getPedido_med());
	}

	private PedidoRespostaDTO transformaEmResposta(Pedido pedido) {
		return new PedidoRespostaDTO(pedido.getId(), pedido.getStatus(), pedido.getJustificativa(),
				pedido.getDataCriacao(), pedido.getUsuario().getId(), pedido.getPedido_med(),
				pedido.getRecebimento().getId());
	}

	// -------------------- LISTAR PEDIDOS POR USUARIO----------
	public List<PedidoRespostaDTO> listarPorUsuario(long id) {
		Usuario usuario = usuarioControle.listar(id).get();
		List<Pedido> pedidosUsuario = repository.findByUsuario(usuario);
		List<PedidoRespostaDTO> listResposta = new ArrayList<PedidoRespostaDTO>();
		for (Pedido pedido : pedidosUsuario) {
			if (pedido.getRecebimento() != null) {
				listResposta.add(transformaEmResposta(pedido));
			} else {
				listResposta.add(transformaEmRespostaSemRecebimento(pedido));
			}
		}
		return listResposta;
	}
	
	public Optional<Pedido> listarNormal(long id) {
		verifyIfObjectExists(id);
		Optional<Pedido> findById = repository.findById(id);
		return findById;
		
	}

	// -------------LISTA UM PEDIDO ---------------
	public PedidoRespostaListDTO listar(long id) {
		verifyIfObjectExists(id);
		Optional<Pedido> findById = repository.findById(id);
		return respostaListar(findById.get());
	}

	private PedidoRespostaListDTO respostaListar(Pedido pedido) {
		List<MedicamentoRespostaDTO> medicamentos = new ArrayList();
		List<PedidoMedicamento> listPedidoMedicamentos = pedido.getPedido_med();
		PedidoRespostaListDTO resposta = null;
		for (PedidoMedicamento pedidoMedicamento : listPedidoMedicamentos) {
			Medicamento med = pedidoMedicamento.getMedicamento();
			med.setQuantidade(pedidoMedicamento.getQtd());
			medicamentos.add(MedicamentoRespostaDTO.transformaEmDTOList(med));
		}

		return new PedidoRespostaListDTO(pedido.getId(), pedido.getStatus(), pedido.getJustificativa(),
				pedido.getDataCriacao(), pedido.getUsuario().getId(), medicamentos, pedido.getRecebimento());
	}

	public void deletarById(long id) {
		verifyIfObjectExists(id);
		repository.deleteById(id);
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
