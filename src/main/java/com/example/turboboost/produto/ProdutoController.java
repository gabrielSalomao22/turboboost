package com.example.turboboost.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
