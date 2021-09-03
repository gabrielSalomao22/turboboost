package com.example.turboboost.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
