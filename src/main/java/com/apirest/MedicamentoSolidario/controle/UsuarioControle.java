package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Email.Mailer;
import com.apirest.MedicamentoSolidario.Email.Mensagem;
import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.config.DataUtil;
import com.apirest.MedicamentoSolidario.dto.UsuarioDTO;
import com.apirest.MedicamentoSolidario.dto.UsuarioRespostaDTO;
import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;
import com.apirest.MedicamentoSolidario.repository.RoleRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Service
public class UsuarioControle {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DataUtil dataUtil;
	@Autowired
	Mailer mailer;

	public Usuario salvar2(UsuarioDTO userDTO) {
		verificaData(userDTO);
		verificaCPF(userDTO.getCpf());
		validaRole(userDTO);
		// criptografa a senha
		String senha = new BCryptPasswordEncoder().encode(userDTO.getSenha());
		userDTO.setSenha(senha);
		// busca o objeto role atraves do nome da role recebido da reqisiçao
		Role role = findRole(userDTO.getRole());
		// valida a role
		if (role == null) {
			throw new ResourceNotFoundException(MenssagemErro() + " NÂO È POSSIVEL CADASTRAR SEM ROLE ");
		} else {
			userDTO.setFullRole(role);
		}
		String data = userDTO.getDataNascimento();
		userDTO.setNascimento(dataUtil.converterData(data));

		Usuario user = userDTO.trsnformaParaObjSalvar();
		return usuarioRepository.save(user);
	}

	public Usuario atualizar(UsuarioDTO usuarioDTO) {
		verifyIfObjectExists(usuarioDTO.getId());
		usuarioDTO = validacoesSalvar(usuarioDTO);
		return usuarioRepository.save(usuarioDTO.trsnformaParaObjEditar());
	}

	private UsuarioDTO validacoesSalvar(UsuarioDTO userDTO) {
		verificaData(userDTO);
		validaRole(userDTO);
		// criptografa a senha
		String senha = new BCryptPasswordEncoder().encode(userDTO.getSenha());
		userDTO.setSenha(senha);
		// busca o objeto role atraves do nome da role recebido da reqisiçao
		Role role = findRole(userDTO.getRole());
		// valida a role
		if (role == null) {
			throw new ResourceNotFoundException(MenssagemErro() + " NÂO È POSSIVEL CADASTRAR SEM ROLE ");
		} else {
			userDTO.setFullRole(role);
		}
		return userDTO;
	}

	public Iterable<UsuarioRespostaDTO> listarTodosNormal() {
		Iterable<Usuario> listar = usuarioRepository.findAll();
		List<UsuarioRespostaDTO> result = new ArrayList<UsuarioRespostaDTO>();
		for (Usuario str : listar) {
			result.add(UsuarioRespostaDTO.transformaEmDTO(str));
		}
		return result;
	}

	public Optional<Usuario> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Usuario> findById = usuarioRepository.findById(id);
		return findById;
	}

	public Usuario buscaCpf(String cpf) {
		Usuario user = usuarioRepository.findByCpf(cpf);
		if (user == null) {
			throw new ResourceNotFoundException(MenssagemErro() + "  não existente para o  CPF: " + user.getCpf());
		}
		return user;
	}

	public UsuarioRespostaDTO listarDTO(long id) {
		verifyIfObjectExists(id);
		Optional<Usuario> findById = usuarioRepository.findById(id);
		UsuarioRespostaDTO resposta = UsuarioRespostaDTO.transformaEmDTO(findById.get());
		return resposta;
	}

	public void deletarById(long id) {
		verifyIfObjectExists(id);
		usuarioRepository.deleteById(id);
	}

	public void deletar(Usuario usuario) {
		verifyIfObjectExists(usuario.getId());
		usuarioRepository.delete(usuario);
	}

	public void emailSenha(String cpf) {
		Usuario user = usuarioRepository.findByCpf(cpf);
		if (user == null) {
			throw new ResourceNotFoundException(MenssagemErro() + " NAO ENCONTRADO para cpf " + cpf);
		}
		String remetente = "contato.medicamentosolidario@gmail.com";
		String destino = user.getEmail();
		if (destino == null) {
			throw new ResourceNotFoundException(MenssagemErro() + " Não possui Email cadastrado ");
		}
		String assunto = "Redfinição de senha";
		// implementar geração de codigo
		String senha = novaSenha(user);
		String corpo = "Olá! \n\n Segue seu codigo para redefinir senha \n\n "+senha;

		mailer.enviar(new Mensagem(remetente, destino, assunto, corpo));
	}

	private String novaSenha(Usuario user) {
		Random random = new Random();
		int numeroInteiroAleatorio = random.nextInt(10);
		String novaSenha = new String();
		for (int i = 6; i < 6; i++) {
			novaSenha = novaSenha.concat(String.valueOf(numeroInteiroAleatorio));
			System.out.println("SENHA  GERADA : " + novaSenha + "\n");
		}
		// criptografa a senha
		String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
		user.setSenha(senhaCriptografada);
		usuarioRepository.save(user);
		return novaSenha;
	}

	// função que busca no banco a role recebida no formulario
	private Role findRole(String pRole) {
		pRole.toUpperCase();
		if (pRole.contains("ADMIN")) {
			return roleRepository.findByNameRole("ROLE_ADMIN");
		} else if (pRole.contains("INTERMEDIADOR")) {
			return roleRepository.findByNameRole("ROLE_INTERMEDIADOR");
		} else if (pRole.contains("USER")) {
			return roleRepository.findByNameRole("ROLE_USER");
		} else
			throw new ResourceNotFoundException(pRole + " ROLE INVALIDA PARA CADASTRO! ");
	}

	// ---------------------// METODOS DE VALIDAÇAO //------------------------------
	private void verificaData(UsuarioDTO userDTO) {
		if (userDTO.getDataNascimento() != null) {
			// isDateValid(userDTO.getDataNascimento());
			dataUtil.isDateValid(userDTO.getDataNascimento());
		}
	}

	private void validaRole(UsuarioDTO userDTO) {
		if (userDTO.getRole().isEmpty()) {
			throw new ResourceNotFoundException(MenssagemErro() + " Campo role esta vazio! ");
		}
	}

	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Usuario> retorno = usuarioRepository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}

	private void verificaCPF(String cpf) {
		Usuario user = usuarioRepository.findByCpf(cpf);
		if (user != null) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  CPF: " + user.getCpf());
		}
	}

	protected String MenssagemErro() {
		String msg = "Usuario";
		return msg;
	}

}
