package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Projeto;
import dao.DaoGanttChart;

@WebServlet("/pages/buscarDatasPlanejamento")
public class BuscarDatasPlanejamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoGanttChart dao = new DaoGanttChart();
       
    public BuscarDatasPlanejamento() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			List<Projeto> projetos = dao.getProjetos();

			if(!projetos.isEmpty()) {
			
			String grantJson = new Gson().toJson(projetos);
			
			response.setStatus(200);
			response.getWriter().write(grantJson);
				
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}
