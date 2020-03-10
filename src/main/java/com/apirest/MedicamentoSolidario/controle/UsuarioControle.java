package com.apirest.MedicamentoSolidario.controle;

import java.util.ArrayList;
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
		Optional<Usuario> ret = verifySave(usuario.getId());
		if (ret.isPresent()) {
			throw new ResourceNotFoundException(MenssagemErro() + " existente para o  ID: " + usuario.getId());

		} else
			return (Usuario) usuarioRepository.save(usuario);
	}

	public Usuario salvar2(UsuarioDTO userDTO) {
		//verifica se a role esta vazia
		if (userDTO.getRole().isEmpty()) {
			throw new ResourceNotFoundException(MenssagemErro() + " Campo role esta vazio! ");
		} else {
			//criptografa a senha 
			String senha = new BCryptPasswordEncoder().encode(userDTO.getSenha());
			userDTO.setSenha(senha);
			userDTO.setFullRole(findRole(userDTO.getRole()));
			Usuario user = userDTO.trsnformaParaObjSalvar();
			return usuarioRepository.save(user);
		}
	}
	//função que busca no banco a role recebida no formulario
	private Role findRole(String pRole) {
		if (pRole.equals("ADMIN")) {
			return roleRepository.findByNameRole("ROLE_" + pRole);
		} else if (pRole.equals("INTERMEDIADOR")) {
			return roleRepository.findByNameRole("ROLE_" + pRole);
		} else
			return roleRepository.findByNameRole("ROLE_USER");
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
	private void verifyIfObjectExists(long id) {
		String msg = MenssagemErro();
		Optional<Usuario> retorno = usuarioRepository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(msg + " nao encontrado para o ID: " + id));
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
