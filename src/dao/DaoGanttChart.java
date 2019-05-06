package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Projeto;
import beans.Serie;
import connection.SingleConnection;

public class DaoGanttChart {
	
	private Connection connection;
	
	public DaoGanttChart() {
		
		connection = SingleConnection.getConnection();
		
	}
	
	public List<Projeto> getProjetos() throws SQLException {
		
		List<Projeto> projetos = new ArrayList<Projeto>();
		
//		SQL carregando projetos:
		String sqlProjeto = "SELECT * FROM projeto";
		PreparedStatement pmt = connection.prepareStatement(sqlProjeto);
		ResultSet rs = pmt.executeQuery();
		
		while(rs.next()) {
			
			Projeto projeto = new Projeto();
			projeto.setId(rs.getLong("id"));
			projeto.setNome(rs.getString("nome"));
			
			String sqlSerie = "SELECT * FROM serie WHERE projeto = " + rs.getLong("id");
			PreparedStatement pmtSer = connection.prepareStatement(sqlSerie);
			ResultSet rsSer = pmtSer.executeQuery();
			
			List<Serie> series = new ArrayList<Serie>();
			
			while(rsSer.next()) {
				
				Serie serie = new Serie();
				serie.setId(rsSer.getLong("id"));
				serie.setNome(rsSer.getString("nome"));
				serie.setDataInicial(rsSer.getString("datainicial"));
				serie.setDataFinal(rsSer.getString("datafinal"));
				serie.setProjeto(rsSer.getLong("projeto"));
				
				series.add(serie);
			}
			
			projeto.setSeries(series);
			projetos.add(projeto);
		}
	
		return projetos;
	}

}
