package com.example.turboboost.pedido;

public enum StatusPedido {

	PROCESSAMENTO("Em processamento"),
	SEPARACAO("Em separação"),
	TRANSPORTE("Em transporte"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado"),
	TROCA("Troca solicitada"),
	TROCA_ACEITA("Troca aceita");
	
	private String descricao;
	
	StatusPedido(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
