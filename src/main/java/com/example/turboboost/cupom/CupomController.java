package com.example.turboboost.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/cupom")
public class CupomController {
	
	@Autowired
	private CupomService service;
	
	@RequestMapping(path = "/visualizar", method = RequestMethod.GET)
	public ModelAndView visualizar() {
		ModelAndView mv = new ModelAndView("copum/cupons");
		
		mv.addObject("cuponsDTO", service.listarCupons());
		
		return mv;
	}

}
