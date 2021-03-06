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
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.dto.MedicamentoDTO;
import com.apirest.MedicamentoSolidario.dto.MedicamentoRespostaDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoDTO;
import com.apirest.MedicamentoSolidario.dto.PedidoMedicamentoDTO;
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
	@Autowired
	PontoColetaControle pontoControle;

	// --------------METODO SALVAR--------------
	public long salvar(PedidoDTO pedidoDTO) {
		Optional<Pedido> ret = verifySave(pedidoDTO.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + pedidoDTO.getId());

		} else {
			try {
				// Salva pedido
				long idPedidoSalvo = repository.save(transformaSalvarPedido(pedidoDTO)).getId();
				System.out.println("Pedido salvo");
				// Cahama metodo para salvar relaça ode medicamentos com pedido
				salvarPed_med(pedidoDTO, idPedidoSalvo);
				System.out.println("Pedido_medicamento salvo");
				// pedido = repository.findById(pedido.getId()).get();

				verifyIfObjectExists(idPedidoSalvo);
				// return
				// transformaEmRespostaSemRecebimento(repository.findById(idPedidoSalvo).get());
				return idPedidoSalvo;

			} catch (Exception e) {
				new ResourceNotFoundException("Erro ao salvar PEDIDO !!");
			}

		}
		return 0;
	}

	// -----------------METODO ATUALIZAR------------------
	public PedidoRespostaListDTO atualizar(PedidoDTO dto) {
		verifyIfObjectExists(dto.getId());
		Pedido pedido = repository.save(transformaEditarPedido(dto));
		return respostaListar(pedido);
	}

	private Pedido transformaEditarPedido(PedidoDTO dto) {
		return new Pedido(dto.getId(), dto.getJustificativa(), getUsuario(dto), getPonto(dto));
	}

	private void salvarPed_med(PedidoDTO dto, long idPedido) {
		verifyIfObjectExists(idPedido);
		
		// forEach com expressao lambda
		/*
		 * dto.getListaMedicamentos().forEach(item -> {
		 * pedidomeMedicamentoRepository.save(new
		 * PedidoMedicamento(repository.findById(idPedido).get(),
		 * medicamentoControle.listar(item.getMedicamentoID()).get(), item.getQtd()));
		 * Medicamento medicamento =
		 * medicamentoControle.listar(item.getMedicamentoID()).get();
		 * medicamento.setQuantidade(medicamento.getQuantidade() - item.getQtd());
		 * medicamentoRepository.save(medicamento); });
		 */
		
		for (PedidoMedicamentoDTO item : dto.getListaMedicamentos()) {
			pedidomeMedicamentoRepository.save(new PedidoMedicamento(repository.findById(idPedido).get(),
					medicamentoControle.listar(item.getMedicamentoID()).get(), item.getQtd()));
			Medicamento medicamento = medicamentoControle.listar(item.getMedicamentoID()).get();
			medicamento.setQuantidade(medicamento.getQuantidade() - item.getQtd());
			medicamentoRepository.save(medicamento);
		}
	}

	public void cancelamentoPedido(long idPedido) {
		Pedido pedido = verifyIfObjectExists(idPedido);
		for (PedidoMedicamento item : pedido.getPedido_med()) {
			Medicamento medicamento = medicamentoControle.listar(item.getMedicamento().getId()).get();
			medicamento.setQuantidade(medicamento.getQuantidade() + item.getQtd());
			medicamentoRepository.save(medicamento);
		}
		pedido.setStatus("cancelado");
		repository.save(pedido);
	}

	private Pedido transformaSalvarPedido(PedidoDTO dto) {
		return new Pedido(dto.getJustificativa(), dataCriacao(), getUsuario(dto), "aberto", getPonto(dto));
	}

	private PontoColeta getPonto(PedidoDTO dto) {

		return pontoControle.listar(dto.getIdPonto()).get();
	}

	private Usuario getUsuario(PedidoDTO dto) {
		return usuarioControle.listar(dto.getIdUsuario()).get();
	}

	private LocalDateTime dataCriacao() {
		return LocalDateTime.now();
	}

	// ----------------METODO LISTAR TODOS-------------------
	public Iterable<PedidoRespostaListDTO> listarTodosNormal() {
		Iterable<Pedido> listaPedidos = repository.findAll();
		List<PedidoRespostaListDTO> result = new ArrayList<PedidoRespostaListDTO>();
		for (Pedido pedido : listaPedidos) {
			result.add(respostaListar(pedido));
		}
		return result;
	}

	// -------------------- LISTAR PEDIDOS POR USUARIO----------
	public List<PedidoRespostaListDTO> listarPorUsuario(long id) {
		Usuario usuario = usuarioControle.listar(id).get();
		List<Pedido> pedidosUsuario = repository.findByUsuario(usuario);
		List<PedidoRespostaListDTO> listResposta = new ArrayList<PedidoRespostaListDTO>();
		for (Pedido pedido : pedidosUsuario) {
			listResposta.add(respostaListar(pedido));
		}
		return listResposta;
	}

	// ------------- LISTA PEDIDOS POR PONTO -------------
	public List<PedidoRespostaListDTO> listarPorPonto(long id) {
		PontoColeta ponto = pontoControle.listar(id).get();
		List<Pedido> pedidosUsuario = repository.findByPonto(ponto);
		List<PedidoRespostaListDTO> listResposta = new ArrayList<PedidoRespostaListDTO>();
		for (Pedido pedido : pedidosUsuario) {
			listResposta.add(respostaListar(pedido));
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
				pedido.getDataCriacao(), pedido.getUsuario(), medicamentos, pedido.getRecebimento(), pedido.getPonto());
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

	private Pedido verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Pedido> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
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
