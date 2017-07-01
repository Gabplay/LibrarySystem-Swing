package backend;

import java.util.List;

public interface AutoresDao {
	void inserir(Autores autor);
	
	void atualizar(Autores autor);
	
	boolean remover(int id);
	
	List<Autores> filtrar(String nome, String pais_origem);
	
	List<Autores> getTodos();
}