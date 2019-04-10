package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

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
			
		String acao = request.getParameter("acao");
		
		if(acao.equalsIgnoreCase("carregar")){
			
			RequestDispatcher view = request.getRequestDispatcher("upload.jsp");
			request.setAttribute("listaUserImagem", usuarioDAO.getUsuarios());
			view.forward(request, response);
			
			
		} else if(acao.equalsIgnoreCase("download")) {
			
			String idUser = request.getParameter("idUser");
			String imagem = usuarioDAO.buscarImagem(idUser);
			
			if(imagem != null) {
//				Pega somente a imagem pura
				String imagemPura = imagem.split(",")[1]; // ele vai quebrar ( , ) e eu vou pegar na posição 1( [1] )

//				Vamos converter base64 para byte:
				byte [] imagemBytes = new Base64().decodeBase64(imagemPura);
				
//				Coloca os bytes em um objetos de 'entrada' pra processar
				InputStream is = new ByteArrayInputStream(imagemBytes);

				
//			    Escrever dados na resposta {
					
					int read = 0; //controle
					byte[] bytes = new byte[1024];
					OutputStream os =  response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush(); //Enviar
					os.close(); //fechar
				
//				}
			}
		
		}
		
		
			
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
		
	}catch(Exception ex) {
		
		ex.printStackTrace();
		response.getWriter().write("Erro!, ");
	}
		
	}

}
