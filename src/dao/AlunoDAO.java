package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Aluno;
import connection.SingleConnection;

public class AlunoDAO {
	
	private static Connection conn;
	
	public AlunoDAO() {
		
		conn = SingleConnection.getConnection();
		
	}
	
	public List<Aluno> getAlunos() throws Exception {
		
		List<Aluno> alunos  = new ArrayList<Aluno>();
		String sql = "SELECT * FROM Aluno";
		PreparedStatement pmt = conn.prepareStatement(sql);
		
		ResultSet rs = pmt.executeQuery();
		
		while(rs.next()) {
			
			Aluno a = new Aluno();
			a.setId(rs.getLong("id"));
			a.setLogin(rs.getString("login"));
			a.setSenha(rs.getString("senha"));
			
			alunos.add(a);
		}
		
		return alunos;		
	}
	
}
