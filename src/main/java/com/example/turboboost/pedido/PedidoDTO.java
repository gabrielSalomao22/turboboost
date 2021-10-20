package com.example.turboboost.pedido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.cupom.CupomPromocional;
import com.example.turboboost.cupom.GerarCodigo;
import com.example.turboboost.produto.Produto;
import com.example.turboboost.produto.ProdutoDAO;
import com.example.turboboost.produto.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private String codigo;
	private ItemPedido[] itens;
	private UUID[] hashProduto;
	private int[] quantidadeItem;
	private double precoTotal;
	private UUID cupomPromocional;
	private UUID[] cupomCliente;
	private UUID hashEndereco;
	private UUID hashCartao;
	private String nomeCliente;
	private String dataFormatada;
	private String status;
	private UUID hashPedido;
	private List<ProdutoDTO> produtosDTO;
	
	public Pedido preencherObjeto(PedidoDTO pedidoDTO, Cliente cliente, ProdutoDAO dao, Endereco endereco, Cartao cartao, List<CupomPromocional> cupons) {
		Pedido pedido = new Pedido();
		
		List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		
		for(int i = 0; i < hashProduto.length; i++) {
			Optional<Produto> produtoOptional = dao.findByHash(hashProduto[i]);
			ItemPedido item = new ItemPedido(produtoOptional.get().getHash().toString(), quantidadeItem[i]);
			itensPedido.add(item);
		}
		
		pedido.setCodigo(GerarCodigo.gerarCodigo());
		pedido.setDataPedido(LocalDate.now());
		pedido.setHashCliente(cliente.getHash().toString());
		pedido.setHashEndereco(endereco.getHash().toString());
		pedido.setHashCartao(cartao.getHash().toString());
		pedido.setItens(itensPedido);
		pedido.setCuponsUtilizados(cupons);
		pedido.setValorTotal(this.precoTotal);
		pedido.setStatus(StatusPedido.PROCESSAMENTO);
		
		return pedido;
	}
	
	public static PedidoDTO preencherDTO(Pedido pedido, Cliente cliente) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		pedidoDTO.setCodigo(pedido.getCodigo());
		pedidoDTO.setHashPedido(pedido.getHash());
		pedidoDTO.setNomeCliente(cliente.getNome());
		pedidoDTO.setDataFormatada(pedido.getDataPedido().format(formatter));
		pedidoDTO.setStatus(pedido.getStatus().getDescricao());
		pedidoDTO.setPrecoTotal(pedido.getValorTotal());
		
		return pedidoDTO;
	}
	
	public static PedidoDTO preencherDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		pedidoDTO.setCodigo(pedido.getCodigo());
		pedidoDTO.setHashPedido(pedido.getHash());
		pedidoDTO.setDataFormatada(pedido.getDataPedido().format(formatter));
		pedidoDTO.setStatus(pedido.getStatus().getDescricao());
		pedidoDTO.setPrecoTotal(pedido.getValorTotal());
		
		return pedidoDTO;
	}
	
	public static PedidoDTO preencherDTO(Pedido pedido, List<ProdutoDTO> produtosDTO) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		pedidoDTO.setHashPedido(pedido.getHash());
		pedidoDTO.setProdutosDTO(produtosDTO);
		pedidoDTO.setCodigo(pedido.getCodigo());
		pedidoDTO.setDataFormatada(pedido.getDataPedido().format(formatter));
		pedidoDTO.setPrecoTotal(pedido.getValorTotal());
		pedidoDTO.setStatus(pedido.getStatus().getDescricao());
		
		return pedidoDTO;
	}
	
}
