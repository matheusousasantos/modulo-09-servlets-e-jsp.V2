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


@WebServlet("/pages/servlet-autenticacao")
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletAutenticacao() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
//      Neste momento pode ser feito a validação no banco de dados.
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
			
			UsuarioLogado user = new UsuarioLogado();
			user.setLogin(login);
			user.setSenha(senha);
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("usuario", user);//Criando
			
			
//			redireciona para o sistema e autoriza
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acessoAoSistema.jsp");
			dispatcher.forward(request, response);
			
			
		} else {//Se o login falhou
//          Redireciona para admin novamente.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
