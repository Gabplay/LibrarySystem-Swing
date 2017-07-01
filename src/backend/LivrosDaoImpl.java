package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivrosDaoImpl implements LivrosDao{
	private Connection con;
	PreparedStatement ps;
	ResultSet result;

	public LivrosDaoImpl() {
		con = new FabricaConexao().getConnection();
	}

	@Override
	public List<Livros> getTodos() {
		List<Livros> lista = new ArrayList<>();
		String sqlSearch = ("SELECT * FROM Livros AS l INNER JOIN Autores AS a ON a.id_autor = l.id_autor");
		try {
			ps = con.prepareStatement(sqlSearch);
			result = ps.executeQuery();
			while (result.next()) {
				Livros livro = new Livros();
				livro.setId(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setIsbn(result.getString("isbn"));
				livro.setEditora(result.getString("editora"));
				livro.setQtdeEstoque(result.getInt("qtde_estoque"));
				livro.setAutor(result.getString("nome"));
				lista.add(livro);
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
	public int getBookIdByIsbn(Livros livro){
		String sqlSearch = ("SELECT id_livro FROM Livros WHERE isbn = ?");
		int id_livro = 0;
		try{
			ps = con.prepareStatement(sqlSearch);
			ps.setString(1, livro.getIsbn());
			result = ps.executeQuery();
			while (result.next()) {
				id_livro = result.getInt("id_livro");
			}
			
			ps.close();
			result.close();
			return id_livro;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_livro;
	}
	
	@Override
	public int getAuthorIdByName(Livros livro){
		String sqlSearch = ("SELECT id_autor FROM Autores WHERE nome = ?");
		int id_autor = 0;
		try{
			ps = con.prepareStatement(sqlSearch);
			ps.setString(1, livro.getAutor());
			result = ps.executeQuery();
			while (result.next()) {
				id_autor = result.getInt("id_autor");
			}
			
			ps.close();
			result.close();
			return id_autor;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_autor;
	}

	@Override
	public void inserir(Livros livro) {
		int id_autor = getAuthorIdByName(livro);
		
		try {
			String sqlInsert = "INSERT INTO Livros (titulo, id_autor, isbn, editora, qtde_estoque) VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sqlInsert);
			ps.setString(1, livro.getTitulo());
			ps.setInt(2, id_autor);
			ps.setString(3, livro.getIsbn());
			ps.setString(4, livro.getEditora());
			ps.setInt(5, livro.getQtdeEstoque());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Livros livro) {
		int id_autor = getAuthorIdByName(livro);
		int id_livro = getBookIdByIsbn(livro);
		
		try {
			String sqlUpdate = "UPDATE Livros SET titulo = ?, id_autor = ?, isbn = ?, editora = ?, qtde_estoque = ? WHERE id_livro = ? LIMIT 1";
			ps = con.prepareStatement(sqlUpdate);
			ps.setString(1, livro.getTitulo());
			ps.setInt(2, id_autor);
			ps.setString(3, livro.getIsbn());
			ps.setString(4, livro.getEditora());
			ps.setInt(5, livro.getQtdeEstoque());
			ps.setInt(6, id_livro);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(String isbn){
		try{
			String sqlDelete = "DELETE FROM Livros WHERE isbn = ?";
			ps = con.prepareStatement(sqlDelete);
			ps.setString(1, isbn);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Livros> filtrar(String titulo, String autor) {
		List<Livros> lista = new ArrayList<>();
		String sqlSearch;
		
		if(!titulo.equals("") && !autor.equals("")){
			sqlSearch = "SELECT * FROM Livros AS l INNER JOIN Autores AS a ON l.id_autor = a.id_autor"
					+ "	 WHERE l.titulo LIKE ? AND a.nome LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(1, "%" + titulo + "%");
				ps.setString(2, "%" + autor + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(titulo.equals("")){
			sqlSearch = "SELECT * FROM Livros AS l INNER JOIN Autores AS a ON l.id_autor = a.id_autor"
					+ "	 WHERE a.nome LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(2, "%" + autor + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(autor.equals("")){
			sqlSearch = "SELECT * FROM Livros AS l INNER JOIN Autores AS a ON l.id_autor = a.id_autor"
					+ "	 WHERE l.titulo LIKE ?";
			try{
				ps = con.prepareStatement(sqlSearch);
				ps.setString(1, "%" + titulo + "%");
				result = ps.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			while (result.next()) {
				Livros livro = new Livros();
				livro.setId(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setIsbn(result.getString("isbn"));
				livro.setEditora(result.getString("editora"));
				livro.setQtdeEstoque(result.getInt("qtde_estoque"));
				livro.setAutor(result.getString("nome"));
				lista.add(livro);
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
