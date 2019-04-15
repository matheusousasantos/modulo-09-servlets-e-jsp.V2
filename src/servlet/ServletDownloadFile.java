package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;

import beans.BeanCursoJsp;
import dao.UsuarioDAO;
import service.RelatorioService;

@WebServlet("/servletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RelatorioService relatorioService = new RelatorioService();
	private UsuarioDAO dao = new UsuarioDAO();
       
   
    public ServletDownloadFile() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			
			ServletContext context = request.getServletContext();
		
			String tipoExportar = request.getParameter("tipoExportar");
		
		
			List<BeanCursoJsp> usuarios = dao.getUsuarios();
			List dados = new ArrayList();
			dados.add(usuarios);
			
			String fileUrl = relatorioService.gerarRelatorio(dados, new HashMap(), "rel_usuario", 
					"rel_usuario", context);
			
//			Contruir o caminho completo e absoluto do arquivo.
			File downloadFile = new File(fileUrl);
			
//			Vamos ler o nosso arquivo:
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
//			Obtermos o tipo MIME do arquivo para a resposta entender o nosso arquivo:
			String mineType = context.getMimeType(fileUrl);
			
			if(mineType == null) {
//				Define como tipo binário se o mapeamento não for encontrado
				mineType = "application/octet-stream"; //Faz o download de qualquer coisa.
			}
			
//			Define o atributo para resposta:
			response.setContentType(mineType);
			response.setContentLength((int) downloadFile.length());
			
//			Definir cabeçalho para a resposta:
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			
			response.setHeader(headerKey, headerValue);
			
//			Obter um fluxo de saída da resposta:
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1; //Quando bytes já foram lidos.
			
//			Escreve os bytes lidos a partir do fluxo de entrada para o fluxo de saída
			
			while((bytesRead = inputStream.read(buffer)) != -1) {
				
				outputStream.write(buffer,0,bytesRead); // escrever o fluxo de entrada resposta
				inputStream.close();
				outputStream.close();
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
