package excecoes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pages/capturarExcecao")
public class CapturarExcecao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CapturarExcecao() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		try {
			String valor = request.getParameter("valorParam");
			Integer.parseInt(valor);
			
			response.setStatus(200);
			response.getWriter().write("Processado com sucesso!");
		
		}catch(Exception e){
			response.setStatus(500);
			response.getWriter().write("Error ao processar! " + e.getMessage());
		
		}	

	}

}
