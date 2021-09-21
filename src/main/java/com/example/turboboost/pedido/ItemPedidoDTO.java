package com.example.turboboost.pedido;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

	private UUID hashProduto;
	private int quantidadeItem;
	
	
	public static ItemPedidoDTO preencherDTO(UUID hashProduto, int quantidadeItem) {
		ItemPedidoDTO itemDTO = new ItemPedidoDTO();
		
		itemDTO.setHashProduto(hashProduto);
		itemDTO.setQuantidadeItem(quantidadeItem);
		
		return itemDTO;
	}
}
