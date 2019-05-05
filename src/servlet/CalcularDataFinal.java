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
			
			String data = request.getParameter("data"); //Referente ao dia inicial... ex(04/05/2019).
			int tempo = Integer.parseInt(request.getParameter("tempo")); //Referente a quantidade de horas trabalhadas ex(16 -> 2d).
			
			if(tempo <= horaDia) { //mesmo dia
				
				Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				
//				Vamos pegar uma instancia do calendar:
				Calendar calendar  = Calendar.getInstance();
				calendar.setTime(dateInformada);
				calendar.add(Calendar.DATE, 1);// Adiciona mais um dia ficando pra amanhã.
				
				dataCalculada = calendar.getTime(); //Retorna a data.
				totalDeDias = 1.0;
				
			} else {// Se não for um dia ou menos:

				totalDeDias = (double) (tempo / horaDia);
				
				if(totalDeDias <= 1) { // Se der um número quebrado vai se tornar um dia:
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				
				} else { // Se for mais, ex: 16h / 8h que será 2 dias.
					Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);//Pegamos a data informada(Data Inicial).
					
//					Vamos pegar uma instancia do calendar:
					Calendar calendar  = Calendar.getInstance();
					calendar.setTime(dateInformada);
					calendar.add(Calendar.DATE, totalDeDias.intValue());// pega o calendario e adiciona mais dias.
//					Com o exemplo acima(Se for mais, ex: 16h / 8h que será 2 dias.) irá passar 2 dias.
					
					dataCalculada = calendar.getTime(); //Retorna a data.
				}
			}
//			Salvando no banco de dados.
			dcdf.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));//Transforma em string formatada como data.
			
			RequestDispatcher view = request.getRequestDispatcher("/pages/data.jsp");
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias", totalDeDias);
			
			view.forward(request, response);
			
		} catch(Exception x) {
			x.getStackTrace();
		}
		
	}

}
