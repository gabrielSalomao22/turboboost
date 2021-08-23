package com.example.turboboost.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dao.ClienteDAO2;
import com.example.turboboost.cliente.dao.Dao;
import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.models.Cliente;

@Controller
public class ClienteController {
	
	
	@Autowired
	private ClienteDAO2 dao;

	@RequestMapping(path = "/novo", method = RequestMethod.GET)
	public ModelAndView novoCadastro() {
		return new ModelAndView("cliente/cadastro");
	}
	
	@RequestMapping(path = "/novo", method = RequestMethod.POST)
	public ModelAndView novoCadastro(ClienteDTO clienteDTO) {
		
		ClienteFacade facade = new ClienteFacade();
		
		String msgErro = facade.validarCadastroInicial(clienteDTO);
		if(msgErro != null) {
			ModelAndView mv = new ModelAndView("cliente/cadastro");
			System.err.println("MSG ERRO = " + msgErro);
			mv.addObject("msgErro", msgErro);
			return mv;
		}
		
		Cliente cliente = new Cliente();
		cliente = clienteDTO.preencherObjeto(cliente);
		dao.save(cliente);
		//clienteDAO.salvar(cliente);
		
		return new ModelAndView("login");
	}
}
