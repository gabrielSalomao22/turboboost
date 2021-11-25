package com.example.turboboost.cliente.models;

public enum Ranking {

	MUITO_BAIXO("Muito baixo"),
	BAIXO("Baixo"),
	REGULAR("REGULAR"),
	ALTO("Alto"),
	MUITO_ALTO("Muito alto");
	
	
	private String descricao;
	
	Ranking(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
