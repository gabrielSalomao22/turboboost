package com.example.turboboost.cliente.models;

public enum TipoEndereco {
	
	ENTREGA("Entrega"),
	COBRANCA("Cobrança"),
	AMBOS("Ambos");
	
	private String descricao;
	
	TipoEndereco(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
