package com.example.turboboost.produto;

public enum Categoria {

	PERFORMANCE("Performance"),
	MANUTENCAO("Manutenção"),
	ESTETICA("Estética");
	
	
	private String descricao;
	
	Categoria(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
