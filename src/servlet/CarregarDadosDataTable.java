package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CarregarDadosDataTable() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		String json = "{"+
			  "\"draw\": 1,"+
			  "\"recordsTotal\": 57,"+
			  "\"recordsFiltered\": 57,"+
			  "\"data\": ["+
			   " ["+
			      "\"Airi\", "+
			      "\"Satou\","+
			      "\"Accountant\","+
			      "\"Tokyo\","+
			      "\"28th Nov 08\","+
			      "\"$162.700\""+
			    "],"+
			    "["+
			      "\"Angelica\", "+
			      "\"Ramos\", " +
			      "\"Chief Executive Officer (CEO)\", "+
			      "\"London\", "+
			      "\"9th Oct 09\", "+
			      "\"$1.200\""+
			   "]"+
			  "]"+
			"}";
		
		System.out.println(json);
		
		response.setStatus(200);//Status da resposta com sucesso 
		response.getWriter().write(json); // JSON de resposta (escreve as resposta HTTP)
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
