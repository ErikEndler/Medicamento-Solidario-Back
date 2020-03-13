package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
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

	public Usuario salvar(Usuario usuario) {
		verificaCPF(usuario.getCpf());
		Optional<Usuario> ret = verifySave(usuario.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + usuario.getId());

		} else
			return (Usuario) usuarioRepository.save(usuario);
	}

	public Usuario salvar2(UsuarioDTO userDTO) {
		//verifica se o cpf passado ja existe		
		verificaCPF(userDTO.getCpf());
		//verifica se a role esta vazia
		if (userDTO.getRole().isEmpty()) {
			throw new ResourceNotFoundException(MenssagemErro() + " Campo role esta vazio! ");
		} 
//		else if(!verificaData(userDTO)) {
//			throw new ResourceNotFoundException(MenssagemErro() + " Campo role esta vazio! ");
//		}
		else{
			//criptografa a senha 
			String senha = new BCryptPasswordEncoder().encode(userDTO.getSenha());
			userDTO.setSenha(senha);
			//busca o objeto role atraves do nome da role recebido da reqisiçao
			Role role =findRole(userDTO.getRole());
			if(role ==null) {
				throw new ResourceNotFoundException(MenssagemErro() + " NÂO È POSSIVEL CADASTRAR SEM ROLE ");
			}else {
				userDTO.setFullRole(role);
			}			
			Usuario user = userDTO.trsnformaParaObjSalvar();
			return usuarioRepository.save(user);
		}
	}

	//função que busca no banco a role recebida no formulario
	private Role findRole(String pRole) {
		pRole.toUpperCase();
		if (pRole.contains("ADMIN")) {
			return roleRepository.findByNameRole("ROLE_" + pRole);
		} else if (pRole.contains("INTERMEDIADOR")) {
			return roleRepository.findByNameRole("ROLE_" + pRole);
		} else if(pRole.contains("USER")) {
			return roleRepository.findByNameRole("ROLE_USER");
		}
			else
				throw new ResourceNotFoundException(MenssagemErro() + " ROLE INVALIDA PARA CADASTRO! ");
	}

	public Iterable<UsuarioRespostaDTO> listarTodosNormal() {
		Iterable<Usuario> listar = usuarioRepository.findAll();
		List<UsuarioRespostaDTO> result = new ArrayList<UsuarioRespostaDTO>();
		for (Usuario str : listar) {
			result.add(UsuarioRespostaDTO.transformaEmDTO(str));
		}
		// UsuarioRespostaDTO transformaEmDTO =
		// UsuarioRespostaDTO.transformaEmDTO((Usuario) listar);
		// Iterable<UsuarioRespostaDTO> resposta = (Iterable<UsuarioRespostaDTO>)
		// transformaEmDTO;
		return result;
	}

	public Optional<Usuario> listar(long id) {
		verifyIfObjectExists(id);
		Optional<Usuario> findById = usuarioRepository.findById(id);
		return findById;
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

	public Usuario atualizar(Usuario usuario) {
		verifyIfObjectExists(usuario.getId());
		return usuarioRepository.save(usuario);
	}

	// ---------------------------------------------------------------//
	private boolean verificaData(UsuarioDTO userDTO) {
		Date data =userDTO.getNascimento();
		//data.
		return false;
	}
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Usuario> retorno = usuarioRepository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
	}
	
	private void verificaCPF(String cpf) {
		Usuario user = usuarioRepository.findByCpf(cpf);
		if(user != null) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  CPF: " + user.getCpf());
		}
		
		
	}
	private Optional<Usuario> verifySave(long id) {
		Optional<Usuario> retorno = usuarioRepository.findById(id);
		return retorno;

	}

	protected String MenssagemErro() {
		String msg = "Objeto";
		return msg;
	}

}
