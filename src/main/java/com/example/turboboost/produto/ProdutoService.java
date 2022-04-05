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
		Optional<Produto> produtoO = dao.findByHash(UUID.fromString(hashProduto));
		Produto produto = produtoO.get();
		
		produto.setHabilitado(false);
		produto.setMotivoInativacao("Optou por exclusão");
		
		dao.saveAndFlush(produto);
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
	
	public void ativar(String hashProduto) {
		Optional<Produto> produtoO = dao.findByHash(UUID.fromString(hashProduto));
		Produto produto = produtoO.get();
		
		produto.setHabilitado(true);
		produto.setMotivoInativacao(null);
		
		dao.saveAndFlush(produto);
	}
	
	public void inativar(String hashProduto, String motivo) {
		Optional<Produto> produtoO = dao.findByHash(UUID.fromString(hashProduto));
		Produto produto = produtoO.get();
		
		produto.setHabilitado(false);
		produto.setMotivoInativacao(motivo);
		
		dao.saveAndFlush(produto);
	}
	
	public void inativarAutomativo(List<ItemPedido> itens) {
		
		for(ItemPedido i : itens) {
			Optional<Produto> produtoO = dao.findByHash(UUID.fromString(i.getHashProduto()));
			Produto produto = produtoO.get();
			
			if(produto.getEstoque() <= 0) {
				produto.setHabilitado(false);
				produto.setMotivoInativacao("Estoque vazio");
			}
			
			dao.saveAndFlush(produto);
		}
		
	}
	
	public ProdutoDTO buscarEdicao(String hashProduto) {
		Optional<Produto> produtoO = dao.findByHash(UUID.fromString(hashProduto));
		ProdutoDTO produtoDTO = ProdutoDTO.preencherDTO(produtoO.get());
		
		return produtoDTO;
	}
	
	public void editar(ProdutoDTO produtoDTO) {
		Optional<Produto> produtoO = dao.findByHash(produtoDTO.getHashProduto());
		Produto produto = produtoO.get();
		
		produto = produtoDTO.preencherObjetoEditar(produto);
		
		dao.saveAndFlush(produto);
	}
	
}
