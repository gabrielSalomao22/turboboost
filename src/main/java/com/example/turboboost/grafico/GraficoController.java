package com.example.turboboost.grafico;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/grafico")
public class GraficoController {
	
	@Autowired
	private GraficoService service;
	
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView buscar() {
		return new ModelAndView("grafico/inicial");
	}
	
	@RequestMapping(path="/buscar", method=RequestMethod.GET)
	public ModelAndView buscar(String dataInicio, String dataFinal) {
		
		ModelAndView mv = new ModelAndView("grafico/exibir");
		
		GraficoDTO dados = service.gerarGrafico2(LocalDate.parse(dataInicio), LocalDate.parse(dataFinal));
		
		mv.addObject("dados", dados);
		
		return mv;
	}
	

}
