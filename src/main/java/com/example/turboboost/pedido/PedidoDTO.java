package com.example.turboboost.pedido;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	
	private ItemPedido[] itens;
	private UUID[] hashProduto;
	private int[] quantidadeItem;
	private double precoTotal;
	private UUID cupomPromocional;
	private UUID[] cupomCliente;
	private UUID hashEndereco;
	private UUID hashCartao;
	
}
