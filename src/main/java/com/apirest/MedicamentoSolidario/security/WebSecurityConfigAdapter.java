package com.apirest.MedicamentoSolidario.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.repository.RoleRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository rolerepository;
	@Autowired
	private MyUserDetailsService myUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (this.usuarioRepository.count() == 0) {
			if (rolerepository.count() == 0) {
				cadastrarRoles();
			}
			cadastraUsuario(usuarioRepository);
		}
		http.cors();//.configurationSource(request -> new  CorsConfiguration().applyPermitDefaultValues());
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers(publicEndPoints()).permitAll()
		//.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
		.antMatchers(HttpMethod.POST).permitAll()
		.antMatchers(HttpMethod.PUT).permitAll()
		.antMatchers(HttpMethod.DELETE).permitAll()
		//.antMatchers(HttpMethod.GET,  "/api/usuario**").permitAll()
		//.antMatchers(HttpMethod.GET,  "/api/pontoColeta**").permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		//.anyRequest().permitAll()
		//.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		//.antMatchers(HttpMethod.PUT).hasRole("ADMIN")
		//.antMatchers("/*/protected/**").hasRole("USER")
		//.antMatchers("/*/admin/**")
		//		.hasRole("ADMIN")
				;
		http.addFilter(new JWTAutenticacaoFilter(authenticationManager()));
		http.addFilter(new JWTAutorizacaoFilter(authenticationManager(),myUserDetailService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

	private String[] publicEndPoints() {
		return new String[] { "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/error/**",
				"/h2-console/**", "/resources/**", "/v2/rest-docs", "/v2/api-docs", "/swagger-resources/configuration",
				"/swagger-resources", "/**.html", "/webjars/**", "/login", "/csrf", "/" };
	}
	public void cadastraUsuario(UsuarioRepository usuarioRepository) {
		Usuario usuario = new Usuario();
		usuario.setCpf("admin");
		usuario.setSenha(passwordEncoder().encode("admin"));
		usuario.setRole(rolerepository.findByNameRole("ROLE_ADMIN"));
		usuarioRepository.save(usuario);
	}

	public void cadastrarRoles() {
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
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://medicamento-solidario.herokuapp.com","*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
   }

}
