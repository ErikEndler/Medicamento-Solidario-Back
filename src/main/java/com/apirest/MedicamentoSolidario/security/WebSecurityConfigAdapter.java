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
    	//rolerepository = null;
        if (usuarioRepository.count() == 0) {
        	System.out.println("Sem usuarios no banco");
        	System.out.println();
        	if(rolerepository.count()==0) {
        		System.out.println("Sem roles cadastradas no banco");
        		System.out.println();
        		Role role = new Role();
            	role.setNameRole("ROLE_ADMIN");
            	rolerepository.save(role);
            	System.out.println("ROLE CRAIADA");
            	System.out.println(role);
        	} 
        	System.out.println("Criando novo usuario");
            Usuario usuario = new Usuario();
            System.out.println("setando login 'CPF'");
            usuario.setCpf("admin");
            usuario.setSenha(passwordEncoder().encode("admin"));
            usuario.setRole(rolerepository.findByNameRole("ROLE_ADMIN"));
            usuarioRepository.save(usuario);
            System.out.println("USUARO CRIADO");
            System.out.println(usuario);
            System.out.println("Usuario admin admin criado");
        }
        builder.userDetailsService(login -> usuarioRepository.findByCpf(login))
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
