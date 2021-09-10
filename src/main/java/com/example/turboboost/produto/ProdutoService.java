package com.example.turboboost.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO dao;
	
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
	
}
