package login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse  = (HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if(!httpRequest.getServletPath().startsWith("/api")) {
			//requisiçao para recurso estatico
			chain.doFilter(request, response);
			return;
		}
		if(httpRequest.getServletPath().startsWith("/api/login")) {
			//usuario tentando  se autenticas
			chain.doFilter(request, response);
			return;
		}
		HttpSession session =httpRequest.getSession(false);
		if(session==null || session.getAttribute("idUsuarioLogado")==null) {
			//cahamada sem autenticaçao
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		//chamada autenticada
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig filterConfig) {
		
	}

}
