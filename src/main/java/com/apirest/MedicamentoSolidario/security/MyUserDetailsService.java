
package com.apirest.MedicamentoSolidario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.repository.RoleRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository rolerepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		if (this.usuarioRepository.count() == 0) {
			if (rolerepository.count() == 0) {
				cadastrarRoles();
			}
			cadastraUsuario(usuarioRepository);
		}
		Usuario usuario = usuarioRepository.findByCpf(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		System.out.println(usuario.getAuthorities());
		System.out.println(new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities()));
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

	private void cadastraUsuario(UsuarioRepository usuarioRepository) {
		Usuario usuario = new Usuario();
		usuario.setCpf("admin");
		usuario.setSenha(passwordEncoder().encode("admin"));
		usuario.setRole(rolerepository.findByNameRole("ROLE_ADMIN"));
		usuarioRepository.save(usuario);
	}

	private void cadastrarRoles() {
		Role role = new Role();
		role.setNameRole("ROLE_ADMIN");
		rolerepository.save(role);
		Role role1 = new Role();
		role1.setNameRole("ROLE_USER");
		rolerepository.save(role1);
		Role role2 = new Role();
		role2.setNameRole("ROLE_INTERMEDIADOR");
		rolerepository.save(role2);
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	public List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
//        List<GrantedAuthority> role_name = new ArrayList<>();
//        role_name.add(user.getRole().getAuthority());
//        
//        return role_name;
//    }

}
