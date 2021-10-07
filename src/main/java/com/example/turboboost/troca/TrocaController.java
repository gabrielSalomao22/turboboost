package com.example.turboboost.troca;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/troca")
public class TrocaController {

	@RequestMapping(path = "/solicitar", method = RequestMethod.POST)
	public ModelAndView novo(TrocaDTO trocaDTO) {
		System.err.println("ta na troca");
		
		for(String s : trocaDTO.getHashProduto()) {
			System.err.println(s);
		}
		
		return new ModelAndView("redirect:/minhasTrocas");
	}
	
}
