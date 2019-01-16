package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.UsuarioLogado;

@WebFilter(urlPatterns= {"/pages/acessoAoSistema.jsp"})
public class FilterAutentificação implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute("usuario");
		
		if(usuarioLogado == null) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		
		System.out.println("Interceptando...");
		
	}

}
