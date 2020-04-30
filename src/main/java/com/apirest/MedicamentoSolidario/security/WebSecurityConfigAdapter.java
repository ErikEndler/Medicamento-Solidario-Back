package com.apirest.MedicamentoSolidario.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.repository.RoleRepository;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
		//http.cors().disable();
		//http.cors().configurationSource(request -> new  CorsConfiguration().applyPermitDefaultValues());
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers(publicEndPoints()).permitAll()
		//.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
		.antMatchers(HttpMethod.POST).permitAll()
		.antMatchers(HttpMethod.PUT).permitAll()
		.antMatchers(HttpMethod.DELETE).permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		//.antMatchers(HttpMethod.GET,  "/api/usuario**").permitAll()
		//.antMatchers(HttpMethod.GET,  "/api/pontoColeta**").permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		//.and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		//.anyRequest().permitAll()
		//.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		//.antMatchers(HttpMethod.PUT).hasRole("ADMIN")
		//.antMatchers("/*/protected/**").hasRole("USER")
		//.antMatchers("/*/admin/**")
		//		.hasRole("ADMIN")
				;
		http.addFilter(new JWTAutenticacaoFilter(authenticationManager(),usuarioRepository));
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
		usuario.setCpf("00000000000");
		usuario.setNome("admin");
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
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(3600L);
		config.setAllowedOrigins(Arrays.asList("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("**", config);
		
		return new CorsFilter(source);
	}
//	@Bean
//	  CorsConfigurationSource corsConfigurationSource() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//	    return source;
//	  }
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","OPTIONS"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();        
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
	
	
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("*")
//			.allowedOrigins("*")
//			.allowedMethods("*")
//				.allowedHeaders("*")
//			
//			.allowCredentials(true).maxAge(3600);
//	}
}
