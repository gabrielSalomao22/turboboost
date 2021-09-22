package com.example.turboboost.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.cupom.CupomPromocional;
import com.example.turboboost.produto.Produto;
import com.example.turboboost.produto.ProdutoDAO;

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
	
	public Pedido preencherObjeto(PedidoDTO pedidoDTO, Cliente cliente, ProdutoDAO dao, Endereco endereco, Cartao cartao, List<CupomPromocional> cupons) {
		Pedido pedido = new Pedido();
		
		List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		
		for(int i = 0; i < hashProduto.length; i++) {
			Optional<Produto> produtoOptional = dao.findByHash(hashProduto[i]);
			ItemPedido item = new ItemPedido(produtoOptional.get().getHash(), quantidadeItem[i]);
			itensPedido.add(item);
		}
		
		pedido.setDataPedido(LocalDate.now());
		pedido.setHashCliente(cliente.getHash());
		pedido.setHashEndereco(endereco.getHash());
		pedido.setHashCartao(cartao.getHash());
		pedido.setItens(itensPedido);
		pedido.setCuponsUtilizados(cupons);
		pedido.setValorTotal(this.precoTotal);
		pedido.setStatus(StatusPedido.PROCESSAMENTO);
		
		return pedido;
	}
	
}
