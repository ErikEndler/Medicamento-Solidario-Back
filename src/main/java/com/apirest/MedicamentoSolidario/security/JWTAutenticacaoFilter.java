package com.apirest.MedicamentoSolidario.security;

import static com.apirest.MedicamentoSolidario.config.SecurityConstants.EXPIRATION_TIME;
import static com.apirest.MedicamentoSolidario.config.SecurityConstants.HEADER_STRING;
import static com.apirest.MedicamentoSolidario.config.SecurityConstants.SECRET;
import static com.apirest.MedicamentoSolidario.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@CrossOrigin(origins = "*")

public class JWTAutenticacaoFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public JWTAutenticacaoFilter(AuthenticationManager authenticationManager,UsuarioRepository usuarioRepository) {
		this.authenticationManager = authenticationManager;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			//user=getfull(user);
			return this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Usuario getFullUser(String user) {
		Usuario userfull= usuarioRepository.findByCpf(user);
		return userfull;
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String userName = ((User) authResult.getPrincipal()).getUsername();
		Usuario User = getFullUser(userName);
		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		String bearerToken = TOKEN_PREFIX + token;
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		// response.getWriter().write("{"+"\"Authorization\": \""+bearerToken+ "\"}");
		response.getWriter().append("{" + "\"Authorization\": \"" + bearerToken + "\","
				+ "\n\"Role\": \""+User.getRole().getNameRole()+"\","
				+ "\n\"Nome\": \""+User.getNome()+"\"}"
				);
	}

}
