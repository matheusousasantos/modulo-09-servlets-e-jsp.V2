package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UsuarioLogado;

@WebServlet("/pages/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletAutenticacao() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if(Boolean.parseBoolean(request.getParameter("deslogar"))) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.invalidate();
			response.sendRedirect("../index.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");//URL do pr�pio sistema
		
//      Neste momento pode ser feito a valida��o no banco de dados.
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
			
			UsuarioLogado user = new UsuarioLogado();
			user.setLogin(login);
			user.setSenha(senha);
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("usuario", user);//Criando
			
			
//			redireciona para o sistema e autoriza
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);//redireciona pro pr�pio sistema.
			dispatcher.forward(request, response);
			
			
		} else {//Se o login 	falhou
//          Redireciona para admin novamente.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
