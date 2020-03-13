package com.apirest.MedicamentoSolidario.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.repository.RoleRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
    private RoleRepository rolerepository;

	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UsuarioRepository usuarioRepository)
            throws Exception {    	
        if (usuarioRepository.count() == 0) {
        	if(rolerepository.count()==0) {
        		cadastrarRoles();
        	}     
        	cadastraUsuario(usuarioRepository);
        }
        builder.userDetailsService(login -> usuarioRepository.findByCpf(login))
                .passwordEncoder(passwordEncoder());
    }
    private void cadastraUsuario(UsuarioRepository usuarioRepository) {
    	Usuario usuario = new Usuario();            
        usuario.setCpf("admin");
        usuario.setSenha(passwordEncoder().encode("admin"));
        usuario.setRole(rolerepository.findByNameRole("ROLE_ADMIN"));
        usuarioRepository.save(usuario);
       // System.out.println("USUARO CRIADO");
       // System.out.println(usuario.getCpf()+usuario.getSenha()+usuario.getRole().getNameRole());
    }
    private void cadastrarRoles() {
		Role role = new Role();
    	role.setNameRole("ROLE_ADMIN");
    	rolerepository.save(role);
    	//System.out.println("ROLE CADASTRADA : "+role.getId()+role.getNameRole());
    	Role role1 = new Role();
    	role1.setNameRole("ROLE_USER");
    	rolerepository.save(role1);
    	//System.out.println("ROLE CADASTRADA : "+role1.getId()+role1.getNameRole());
    	Role role2 = new Role();
    	role2.setNameRole("ROLE_INTERMEDIADOR");
    	rolerepository.save(role2);
    	//System.out.println("ROLE CADASTRADA : "+role2.getId()+role2.getNameRole());    	
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
