package com.example.turboboost.usuario;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.commons.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDao dao;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("common/login");
	}
	
	@RequestMapping(path = "/logout")
	public ModelAndView logout() {
		System.err.println("teste logout");
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path = "/bemVindo")
	public ModelAndView bemVindo(Principal principal) {
		Optional<Usuario> usuario = dao.findByEmail(principal.getName());
		
		if(usuario.get().getRole().getDescricao().equals("Cliente")) {
			System.err.println("Cliente");
			return new ModelAndView("redirect:/bemVindoCliente");
			
		}else {
			System.err.println("Admin");
			return null;
		}
		
		
	}
}
