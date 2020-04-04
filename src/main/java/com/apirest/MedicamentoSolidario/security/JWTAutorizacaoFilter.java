package com.apirest.MedicamentoSolidario.security;

import static com.apirest.MedicamentoSolidario.config.SecurityConstants.HEADER_STRING;
import static com.apirest.MedicamentoSolidario.config.SecurityConstants.SECRET;
import static com.apirest.MedicamentoSolidario.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


public class JWTAutorizacaoFilter extends BasicAuthenticationFilter {
	
	private MyUserDetailsService myUserDetailsService;
	//private UsuarioRepository ur;

	public JWTAutorizacaoFilter(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService) {
		super(authenticationManager);
		this.myUserDetailsService = myUserDetailsService;
		
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_STRING);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null)
			return null;
		String username = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		//Usuario user = ur .findByCpf(username);
		//System.out.println("USERNAME : "+username);
		//System.out.println("USERNAME : "+myUserDetailsService.loadUserByUsername(username));
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		//System.out.println("USERNAME : "+userDetails);
		return username != null
				? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
				: null;
	}

}
