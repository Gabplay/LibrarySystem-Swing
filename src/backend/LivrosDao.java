package backend;

import java.util.List;

public interface LivrosDao {
	void inserir(Livros livro);
	
	void atualizar(Livros livro);
	
	void remover(String isbn);
	
	List<Livros> filtrar(String autor, String editora);
	
	List<Livros> getTodos();
	
	int getAuthorIdByName(Livros livro);
	
	int getBookIdByIsbn(Livros livro);
}