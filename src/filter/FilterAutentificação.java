package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;
import user.UsuarioLogado;

@WebFilter(urlPatterns= {"/pages/*"})
public class FilterAutentificação implements Filter {
	
	private static Connection connection;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String urlParaAutenticar = req.getServletPath();
		
		UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute("usuario");

		if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
		                                                                                   
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url=" + urlParaAutenticar);
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		
		System.out.println("Interceptando...");
		
	}
	
	@Override
	public void init(FilterConfig agr0) throws ServletException{
		connection = SingleConnection.getConnection();
	}
	
}
