package br.com.db1.model;

public class UsuarioConsignet {

	private String id;
	private String nome;
	
	public UsuarioConsignet(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
