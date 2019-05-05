package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;


@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DaoCalculaDataFinal dcdf = new DaoCalculaDataFinal();
	

    public CalcularDataFinal() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			
			int horaDia = 8; // As horas de trabalho no dia.
			Date dataCalculada = null;
			Double totalDeDias = 0.0;
			
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horaDia) { //mesmo dia
				
				Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				
//				Vamos pegar uma instancia do calendar:
				Calendar calendar  = Calendar.getInstance();
				calendar.setTime(dateInformada);
				calendar.add(Calendar.DATE, 1);// Adiciona mais um dia ficando pra amanhã.
				
				dataCalculada = calendar.getTime(); //Retorna a data.
				totalDeDias = 1.0;
				
			} else {

				totalDeDias = (double) (tempo / horaDia);
				
				if(totalDeDias <= 1) { // Se der um número querado vai se tornar um dia:
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} else {
					Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
					
//					Vamos pegar uma instancia do calendar:
					Calendar calendar  = Calendar.getInstance();
					calendar.setTime(dateInformada);
					calendar.add(Calendar.DATE, totalDeDias.intValue());// Adiciona mais um dia.
					
					dataCalculada = calendar.getTime(); //Retorna a data.
				}
			}
//			Salvando no banco de dados.
			dcdf.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));//quando for string
			
			RequestDispatcher view = request.getRequestDispatcher("/pages/data.jsp");
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias", totalDeDias);
			
			view.forward(request, response);
			
		} catch(Exception x) {
			x.getStackTrace();
		}
		
	}

}
