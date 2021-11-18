package com.example.turboboost.produto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {

	private final ProdutoService service;
	
	@Autowired
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@RequestMapping(path = "/visualizar", method = RequestMethod.GET)
	public ModelAndView visualizar() {
		ModelAndView mv = new ModelAndView("produto/listaProdutos");
		
		mv.addObject("produtosDTO", service.listarProdutos());
		
		return mv;
	}
	
	@RequestMapping(path = "/novoProduto", method = RequestMethod.POST)
	public ModelAndView novo(ProdutoDTO produtoDTO, @RequestParam("imagemUpload")MultipartFile imagemUpload) throws IOException {			
		service.salvar(produtoDTO, imagemUpload);
		
		return new ModelAndView("redirect:/produto/visualizar");
	}
	
	@RequestMapping(path = "/deletarProduto", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void excluirProduto(String hashProduto) {
		service.deletar(hashProduto);
	}
	
	@RequestMapping(path = "/ativarProduto", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void ativarProduto(String hashProduto) {
		service.ativar(hashProduto);
	}
	
	@RequestMapping(path = "/inativarProduto", method = RequestMethod.POST)
	public ModelAndView inativarProduto(String hashProduto, String motivo) {
		service.inativar(hashProduto, motivo);
		
		return new ModelAndView("redirect:/produto/visualizar");
	}
	
}
