package com.example.turboboost.pedido;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoController {

	
	@RequestMapping(path = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(PedidoDTO pedidoDTO) {
		
		for(UUID hash : pedidoDTO.getHashProduto()) {
			System.err.println(hash.toString());
		}
		
		for(int i : pedidoDTO.getQuantidadeItem()) {
			System.err.println(i);
		}
		
		return new ModelAndView("redirect:/");
	}
	
	
}
