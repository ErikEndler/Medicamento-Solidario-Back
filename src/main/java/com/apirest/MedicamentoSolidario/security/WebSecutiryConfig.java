package com.apirest.MedicamentoSolidario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.dto.UsuarioDTO;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter  {

	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }
	
	@Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UsuarioRepository usuarioRepository)
            throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario usuario = new Usuario();
            usuario.setCpf("admin");
            usuario.setSenha(passwordencoder().encode("admin"));
            usuarioRepository.save(usuario);
        }

        builder.userDetailsService(cpf -> new UsuarioDTO(usuarioRepository.findByCpf(cpf)))
                .passwordEncoder(passwordencoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				/*DETERMINA QUE PARA ACESSAR A PÁGINA swagger-ui.html DA APLICAÇÃO PRECISA ESTÁ AUTENTICADO*/
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
				.anyRequest().authenticated()
				
				.and().formLogin()
				/*
				 * INFORMANDO O CAMINHO DA PÁGINA DE LOGIN, E SE O LOGIN FOR EFETUADO COM
				 * SUCESSO O USUÁRIO DEVE SER REDIRECIONADO PARA
				 * /home(http://localhost:8080/swagger-ui.html)
				 */
				.loginPage("/").defaultSuccessUrl("/swagger-ui.html", true).permitAll().and()
				/*
				 * AQUI ESTAMOS INFORMANDO QUE QUANDO FOR REDIRECIONADO PARA O LINK
				 * http://localhost:8095/logout O USUÁRIO DEVE TER SUA SESSÃO FINALIZADA E
				 * REDIRECIONADO PARA A PÁGINA DE LOGIN
				 */
				.logout().logoutSuccessUrl("/").logoutUrl("/logout").permitAll();
		; /* AQUI ESTAMOS INFORMANDO QUE TODOS TEM ACESSO A PÁGINA DE LOGIN */

	}

	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

}
