package com.example.turboboost.commons;

public enum TipoUsuario {

	CLIENTE("Cliente"),
	ADMIN("Admin");
	
	private String descricao;
	
	TipoUsuario(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
