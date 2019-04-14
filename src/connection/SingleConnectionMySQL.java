package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionMySQL {
																		
	private static String url = "jdbc:mysql://localhost:3306/javaEstudo-m9?autoReconnect=true";
	private static String password = "admin";
	private static String user = "admin";
	private static Connection connection = null;
	 
	static {
		conectar();	
	}
	
	public SingleConnectionMySQL() {
		conectar();
	}
	
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("com.msql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou com sucesso!");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
