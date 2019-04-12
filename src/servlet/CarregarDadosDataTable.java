package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Aluno;
import dao.AlunoDAO;


@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AlunoDAO alunoDAO = new AlunoDAO();
       
    
    public CarregarDadosDataTable() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
		
			List<Aluno> alunos = alunoDAO.getAlunos();
			
			String data = "";
			int totalAlunos = alunos.size();
			int index = 1;
			
			for(Aluno aluno : alunos) {
				
				 data += " ["+                     //data += - concateção de strings em Java, vai somando as strings
						  "\"" + aluno.getId() + "\", "+
						   "\"" + aluno.getLogin() + "\""+
						 "]" ;
				
				 if(index < totalAlunos) { // Se o index for menos que o total quer dizer que ainda não é o ultimo dado.
//					 Então vamos colocar a virgula no fim do dado:
					 data += ",";
				 }
				 
				 index++;
				 
			}
			
			if(!alunos.isEmpty()) {
			
				String json = "{"+         
					  "\"draw\": 1,"+                                    // Imprime uma tabela.
					  "\"recordsTotal\": " + alunos.size() + ","+        // Entre o total de alunos, '.size()' pega a quantidade.
					  "\"recordsFiltered\": "+ alunos.size() +","+		 // Aqui também pra tabela saber a quantidade de registros.
					  "\"data\": ["                                      // Representado por um JSON do dados.
					   + data +                                          // referente ao JSON incorporado
					  "]"+                                               // Fechamento do Data
					"}";                                                 // Fechamento do JSON
			
				
				System.out.println(json);
				
				response.setStatus(200);//Status da resposta com sucesso 
				response.getWriter().write(json); // JSON de resposta (escreve as resposta HTTP)
			}
		
		} catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
