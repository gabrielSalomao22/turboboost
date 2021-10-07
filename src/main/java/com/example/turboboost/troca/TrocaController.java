package com.example.turboboost.troca;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/troca")
public class TrocaController {
	
	@Autowired
	private TrocaService service;

	@RequestMapping(path = "/solicitar", method = RequestMethod.POST)
	public ModelAndView novo(TrocaDTO trocaDTO, Principal principal) {
		
		
		service.novaTroca(trocaDTO, principal);
		
		return new ModelAndView("redirect:/minhasTrocas");
	}
	
}
