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
import org.springframework.web.cors.CorsConfiguration;

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
			if (rolerepository.count() == 0) {
				cadastrarRoles();
			}
			cadastraUsuario(usuarioRepository);
		}
		builder.userDetailsService(login -> usuarioRepository.findByCpf(login)).passwordEncoder(passwordEncoder());
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

	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		// http.formLogin().permitAll();
		http.authorizeRequests().antMatchers(publicEndPoints()).permitAll().antMatchers(HttpMethod.POST, "/api/usuario")
				.permitAll().antMatchers(HttpMethod.GET, "/api/usuario").permitAll().anyRequest().authenticated();
//        http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	private String[] publicEndPoints() {
        return new String[] {  "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**",
        		 "/error/**",
        "/h2-console/**", "/resources/**", "/v2/rest-docs", "/v2/api-docs",
        "/swagger-resources/configuration", "/swagger-resources", "/**.html",
        "/webjars/**", "/login", "/csrf", "/" };
      }

}
