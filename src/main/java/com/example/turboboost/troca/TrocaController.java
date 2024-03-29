package com.example.turboboost.troca;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@RequestMapping(path = "/listarTrocas", method = RequestMethod.GET)
	public ModelAndView listarAdmin() {
		ModelAndView mv = new ModelAndView("troca/troca");
		
		mv.addObject("trocasDTO", service.listarTrocas());
		
		return mv;
	}
	
	@RequestMapping(path = "/alterarStatus", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void alterarStatus(String hashTroca) {
		
		System.err.println(hashTroca);
		
		service.alterarStatus(hashTroca);
		
	}
	
	@RequestMapping(path = "/aceitarRecusar", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void alterarStatus(String hashTroca, boolean status) {
		
		service.aceitarRecusar(hashTroca, status);
		
	}
}

