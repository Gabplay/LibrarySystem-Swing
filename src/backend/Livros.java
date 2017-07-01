package backend;

public class Livros {
	private int id, qtde_estoque;
	private String autor, titulo, isbn, editora;
	
	public Livros(){
		
	}
	public Livros(int id, String titulo, String autor, String isbn, String editora, int qtde_estoque) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.editora = editora;
		this.qtde_estoque = qtde_estoque;
	}
	public Livros(String isbn, String titulo, String autor,  String editora, int qtde_estoque) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.editora = editora;
		this.qtde_estoque = qtde_estoque;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQtdeEstoque() {
		return qtde_estoque;
	}
	public void setQtdeEstoque(int qtde_estoque) {
		this.qtde_estoque = qtde_estoque;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
}
