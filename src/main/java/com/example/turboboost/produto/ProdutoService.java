package com.example.turboboost.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.turboboost.pedido.ItemPedido;
import com.example.turboboost.pedido.Pedido;
import com.example.turboboost.pedido.PedidoDAO;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO dao;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	public List<ProdutoDTO> listarProdutos() {
		List<Produto> produtos = dao.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for(Produto p : produtos) {
			produtosDTO.add(ProdutoDTO.preencherDTO(p));
		}
		
		return produtosDTO;
	}
	
	public void salvar(ProdutoDTO produtoDTO, MultipartFile file) throws IOException {
		
		Produto produto = produtoDTO.preencherObjeto(new Produto(), file);
		
		dao.saveAndFlush(produto);
		
	}
	
	public void deletar(String hashProduto) {
		dao.deletarByHash(UUID.fromString(hashProduto));
	}
	
	public List<ProdutoDTO> listarParaVenda(){
		List<Produto> produtos = dao.buscarParaVenda();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for(Produto p : produtos) {
			produtosDTO.add(ProdutoDTO.preencherDTO(p));
		}
		
		return produtosDTO;
	}
	
	public List<ProdutoDTO> exibirCarrinho(String[] itemCarrinho){
		List<Produto> produtos = new ArrayList<Produto>();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for(String s : itemCarrinho) {
			produtos.add(dao.findByHash(UUID.fromString(s)).get());
		}
		
		for(Produto p : produtos) {
			produtosDTO.add(ProdutoDTO.preencherDTO(p));
		}
		
		return produtosDTO;
	}
	
	public void dimunuirEstoque(List<ItemPedido> itens) {
		
		for(ItemPedido i : itens) {
			Optional<Produto> produtoO = dao.findByHash(UUID.fromString(i.getHashProduto()));
			Produto produto = produtoO.get();
			
			produto.setEstoque(produto.getEstoque() - i.getQuantidadeItem());
			
			dao.saveAndFlush(produto);
		}
		
	}
	
	public void aumentarEstoque(String hashPedido) {
		Optional<Pedido> pedidoO = pedidoDAO.findByHash(UUID.fromString(hashPedido));
		
		Pedido pedido = pedidoO.get();
		List<ItemPedido> itens = pedido.getItens();
		
		for(ItemPedido i : itens) {
			Optional<Produto> produtoO = dao.findByHash(UUID.fromString(i.getHashProduto()));
			Produto produto = produtoO.get();
			
			produto.setEstoque(produto.getEstoque() + i.getQuantidadeItem());
			
			dao.saveAndFlush(produto);
		}
	}
	
}
