package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Evento;
import connection.SingleConnection;

public class EventoDAO {

	private Connection connection;

	public EventoDAO() {

		connection = SingleConnection.getConnection();
	}

	
	
	public void delete(String id) {
		
		String sql = "DELETE FROM usuario WHERE id = '"+id+"'AND login <> 'admin'";
		try {
			PreparedStatement pmt = connection.prepareStatement(sql);
			pmt.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	

	public List<Evento> getEventos() throws SQLException {
		
		List<Evento> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM eventos";
		PreparedStatement pmt = connection.prepareStatement(sql);
		
		ResultSet rs = pmt.executeQuery();
		
		while(rs.next()) {
			
			Evento evento = new Evento();
			evento.setId(rs.getString("id"));
			evento.setDataevento(rs.getString("dataevento"));
			evento.setDescricao(rs.getString("descricao"));
			
			
			lista.add(evento);
		}
		
		return lista;
		
	}
}
