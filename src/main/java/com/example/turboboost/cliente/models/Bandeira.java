package com.example.turboboost.cliente.models;

public enum Bandeira {

	VISA("Visa"),
	MASTERCARD("Mastercard"),
	MAESTRO("Maestro"),
	ELO("Elo"),
	AMERICAEX("American Express");
	
	private String descricao;
	
	Bandeira(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
