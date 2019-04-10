package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public FileUpload() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
		
		RequestDispatcher view = request.getRequestDispatcher("upload.jsp");
		
		request.setAttribute("listaUserImagem", usuarioDAO.getUsuarios());
			
		view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	try {
		
		String fileUpload = request.getParameter("fileUpload");
		
		usuarioDAO.gravarImagem(fileUpload);
	
		response.getWriter().write("Upload realizado com sucesso!!");
		
		System.out.println("Caiu no Seervlet...");
		
	}catch(Exception ex) {
		
		ex.printStackTrace();
		response.getWriter().write("Erro!, ");
	}
		
	}

}
