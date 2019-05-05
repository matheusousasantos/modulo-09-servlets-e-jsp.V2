package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import beans.Evento;
import dao.EventoDAO;


@WebServlet("/pages/buscarCalendarioDatas")
public class BuscarCalendarioDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EventoDAO dao = new EventoDAO();
       
    
    public BuscarCalendarioDatas() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			
			List<Evento> eventos = dao.getEventos();
			
			if(!eventos.isEmpty()) { //Se existir alguma coisa na nossa lista
				
				int totalEventos = eventos.size();//Usando pra saber o tamanho total da lista de eventos
				int index = 1;
				
				String datas = "["; //Todas as listas começam com "["
				
				for(Evento e : eventos) {
//					Processamento:
					datas+= "{ \"title\": \""+e.getDescricao()+"\", \"start\": \""+e.getDataevento()+"\"}";
					
					if(index < totalEventos) {
						datas += ",";
					}
					
					index++;
					
				}
				
				datas+= "]";//terminando com(fim do JSON)
				
				response.setStatus(200);
				response.getWriter().write(datas);
				
			}
		
		
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

	}

}
