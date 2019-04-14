package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection2 {
																		//   Se a conex�o cair ele vai conectar automaticamente
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	 
	static {
		conectar();	/*Vamos criar uma chamada est�tica para nosso m�todo conectar. A partir do momento que invocar-mos essa classe,
			  		de qualquer forma ela vai execultar o conectar() garantindo nossa conex�o com o BD*/
	}
	
	public SingleConnection2() {
		conectar();
	}
	
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); //N�o quero que a minha transa��o commita automaticamento
				System.out.println("Conectou com sucesso!");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados");
		}
	}
	
	public static Connection getConnection() { //Esse m�todo que mais trazer a cone��o.
		return connection;
	}

}
