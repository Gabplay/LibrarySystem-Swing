package backend;

import java.sql.Date;

public class Autores {
	private int id;
	private String nome;
	private Date data_nascimento;
	private String pais;
	
	public Autores(){
		
	}
	public Autores(int id, String nome, Date data_nascimento, String pais) {
		this.id = id;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.pais = pais;
	}
	public Autores(String nome, Date data_nascimento, String pais) {
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.pais = pais;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return data_nascimento;
	}
	public void setDataNascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}
