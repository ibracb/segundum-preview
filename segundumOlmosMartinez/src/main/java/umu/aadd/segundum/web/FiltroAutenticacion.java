package umu.aadd.segundum.web;

import java.io.IOException;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*.xhtml")
public class FiltroAutenticacion implements Filter {
	
	private static final String[] RECURSOS_PUBLICOS = {
		    "/javax.faces.resource/",
		    "/resources/"
	};
	
	private static final String[] PAGINAS_PUBLICAS = {
			"/segundum/inicio.xhtml",
			"/segundum/login.xhtml",
			"/segundum/registro.xhtml"
	};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		String pagina = uri.substring(contextPath.length());
		
		boolean esRecursoPublico = false;
		for (String recurso : RECURSOS_PUBLICOS) {
		    if (pagina.startsWith(recurso)) {
		        esRecursoPublico = true;
		        break;
		    }
		}
		
		boolean esPaginaPublica = false;
		for (String publica : PAGINAS_PUBLICAS) {
			if (pagina.equals(publica)) {
				esPaginaPublica = true;
				break;
			}
		}

		SesionUsuario sesionUsuario = CDI.current().select(SesionUsuario.class).get();
		boolean autenticado = sesionUsuario.isAutenticado();

		if (esRecursoPublico || esPaginaPublica || autenticado) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(contextPath + "/segundum/inicio.xhtml");
		}
	}

	@Override
	public void destroy() {
	}
}