package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		String sql ="INSERT INTO usuario(login, senha, nome, telefone, cep, rua, bairro, "
				+ "cidade, estado, ibge, fotobase64, contenttype, curriculobase64, "
				+ "contenttypecurriculo, fotobase64miniatura, ativo, sexo, perfil) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10,usuario.getIbge());
		
			
			insert.execute();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
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
	

	public List<BeanCursoJsp> getUsuarios() throws SQLException {
		
		List<BeanCursoJsp> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM usuario";
		PreparedStatement pmt = connection.prepareStatement(sql);
		
		ResultSet rs = pmt.executeQuery();
		
		while(rs.next()) {
			
			BeanCursoJsp obj = new BeanCursoJsp();
			obj.setId(rs.getLong("id"));
			obj.setLogin(rs.getString("login"));
			obj.setSenha(rs.getString("senha"));
			obj.setNome(rs.getString("nome"));
			obj.setTelefone(rs.getString("telefone"));
			obj.setCep(rs.getString("cep"));
			obj.setRua(rs.getString("rua"));
			obj.setBairro(rs.getString("bairro"));
			obj.setCidade(rs.getString("cidade"));
			obj.setEstado(rs.getString("estado"));
			obj.setIbge(rs.getString("ibge"));
			obj.setImagem(rs.getString("imagem"));
			
			
			lista.add(obj);
		}
		
		return lista;
		
	}
	
	
	public BeanCursoJsp consultar(String id) {
		
		String sql = "SELECT * FROM usuario WHERE id = '"+id+"'AND login <> 'admin'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				BeanCursoJsp obj = new BeanCursoJsp();
//				Traz o Id tamb�m
				obj.setId(rs.getLong("id"));
				obj.setLogin(rs.getString("login"));
				obj.setSenha(rs.getString("senha"));
				obj.setNome(rs.getString("nome"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCep(rs.getString("cep"));
				obj.setRua(rs.getString("rua"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				obj.setIbge(rs.getString("ibge"));
			
				
				return obj;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {
//		Vai execultar a Atualiza��o
		
		StringBuilder sql = new StringBuilder();

		sql.append(" UPDATE usuario SET login = ?, senha = ?, nome = ?, "); // Essa parte ser� em comum entre todos eles.
		sql.append(" telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, ");
		sql.append(" estado = ?, ibge = ?, ativo = ?, sexo = ?, perfil = ?");
		
		try {
			
			PreparedStatement pmt = connection.prepareStatement(sql.toString());
			pmt.setString(1, usuario.getLogin());
			pmt.setString(2, usuario.getSenha());
			pmt.setString(3, usuario.getNome());
			pmt.setString(4, usuario.getTelefone());
			pmt.setString(5, usuario.getCep());
			pmt.setString(6, usuario.getRua());
			pmt.setString(7, usuario.getBairro());
			pmt.setString(8, usuario.getCidade());
			pmt.setString(9, usuario.getEstado());
			pmt.setString(10,usuario.getIbge());
		
			connection.commit();
			
			pmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean validarLogin(String login) throws Exception{
		
		String sql = "SELECT COUNT(1) qtd FROM usuario WHERE login = '" +login+ "'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				
				return rs.getInt("qtd") <= 0;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean validarLoginUpdate(String login, String id) throws Exception{
		
		String sql = "SELECT COUNT(1) qtd FROM usuario WHERE login = '" +login+ "' AND id <> " + id + "'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				
				return rs.getInt("qtd") <= 0;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

public boolean validarSenha(String senha) throws Exception {
	
	String sql = "SELECT COUNT(1) qtd FROM usuario WHERE senha = '" + senha + "'";
	try {
	PreparedStatement pmt = connection.prepareStatement(sql);
	ResultSet rs = pmt.executeQuery();
	
		if(rs.next()) {
			
			return rs.getInt("qtd") <= 0;
			
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return false;
}

public void gravarImagem(String fileUpload) throws SQLException {
	
	String sql = "INSERT INTO usuario (imagem) VALUES(?);";
	
	PreparedStatement pmt = connection.prepareStatement(sql);
	pmt.setString(1, fileUpload); //que ser� a nossa imagem
	
	pmt.execute();
	
	System.out.println("Saiu no banco de dados...");
	
}
	
	

}