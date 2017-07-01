package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoresDaoImpl implements AutoresDao{
	private Connection con;
	PreparedStatement ps;
	ResultSet result;

	public AutoresDaoImpl() {
		con = new FabricaConexao().getConnection();
	}

	@Override
	public List<Autores> getTodos() {
		List<Autores> lista = new ArrayList<>();
		String sqlSearch = ("SELECT * FROM Autores");
		try {
			ps = con.prepareStatement(sqlSearch);
			result = ps.executeQuery();
			while (result.next()) {
				Autores autor = new Autores();
				autor.setId(result.getInt("id_autor"));
				autor.setNome(result.getString("nome"));
				autor.setDataNascimento(result.getDate("data_nascimento"));
				autor.setPais(result.getString("pais_origem"));
				lista.add(autor);
			}
			ps.close();
			result.close();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void inserir(Autores autor) {
		try {
			String sqlInsert = "INSERT INTO autores (nome, data_nascimento, pais_origem) VALUES (?, ?, ?)";
			ps = con.prepareStatement(sqlInsert);
			ps.setString(1, autor.getNome());
			ps.setDate(2, autor.getDataNascimento());
			ps.setString(3, autor.getPais());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Autores autor) {
		try {
			String sqlUpdate = "UPDATE autores SET nome = ?, data_nascimento = ?, pais_origem = ? WHERE id_autor = ? LIMIT 1";
			ps = con.prepareStatement(sqlUpdate);
			ps.setString(1, autor.getNome());
			ps.setDate(2, autor.getDataNascimento());
			ps.setString(3, autor.getPais());
			ps.setInt(4, autor.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean remover(int id){
		try{
			String sqlDelete = "DELETE FROM autores WHERE id_autor = ?";
			ps = con.prepareStatement(sqlDelete);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Autores> filtrar(String nome, String pais_origem) {
		List<Autores> lista = new ArrayList<>();
		String sqlSearch;
		
		if(!nome.equals("") && !pais_origem.equals("")){
			sqlSearch = "SELECT * FROM Autores WHERE nome LIKE ? AND pais_origem LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(1, "%" + nome + "%");
				ps.setString(2, "%" + pais_origem + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(nome.equals("")){
			sqlSearch = "SELECT * FROM Autores WHERE pais_origem LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(1, "%" + pais_origem + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(pais_origem.equals("")){
			sqlSearch = "SELECT * FROM Autores WHERE nome LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(1, "%" + nome + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			while (result.next()) {
				Autores autor = new Autores();
				autor.setId(result.getInt("id_autor"));
				autor.setNome(result.getString("nome"));
				autor.setDataNascimento(result.getDate("data_nascimento"));
				autor.setPais(result.getString("pais_origem"));
				lista.add(autor);
			}
			ps.close();
			result.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
