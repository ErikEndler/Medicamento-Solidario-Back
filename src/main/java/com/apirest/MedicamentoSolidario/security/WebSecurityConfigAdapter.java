package com.apirest.MedicamentoSolidario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
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

}
