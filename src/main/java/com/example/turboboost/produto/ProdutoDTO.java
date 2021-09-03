package com.example.turboboost.produto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private UUID hashProduto;
	private String sku;
	private String nome;
	private double preco;
	private int estoque;
	private String imagem;
	private String status;
	
	public static ProdutoDTO preencherDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		produtoDTO.setHashProduto(produto.getHash());
		produtoDTO.setSku(produto.getSku());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setEstoque(produto.getEstoque());
		produtoDTO.setImagem(produto.getImagem());
		produtoDTO.setStatus(produto.getStatus());
		
		return produtoDTO;
	}
}
