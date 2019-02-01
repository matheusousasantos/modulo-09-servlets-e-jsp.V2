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

@WebFilter(urlPatterns= {"/pages/*"})
public class FilterAutentificação implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String urlParaAutenticar = req.getServletPath();//url do própio sistema
		
		UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute("usuario");//Recuperando
		
//                      A url precisa ser diferênte da gerada quando o login e senha não são iguais aos previamente definidos
//                      Essa url("/pages/ServletAutenticacao") é a gerada no http quando caimos na página autentificar.jsp
		if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
//			                                                                                     Depois que for autenticado vai ser direcionado
//			                                                                                     pra essa URL, por isso ela precisa ser passada
//			                                                                                     como parâmentro(get)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url=" + urlParaAutenticar);
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		
		System.out.println("Interceptando...");
		
	}

}
