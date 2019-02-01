package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileUpload() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	try {
		
		String fileUpload = request.getParameter("fileUpload");
		System.out.println(fileUpload); //Imagem em base64
		
		response.getWriter().write("Upload realizado com sucesso!!");
		
	}catch(Exception ex) {
		response.getWriter().write("Erro!");
	}
		
	}

}
