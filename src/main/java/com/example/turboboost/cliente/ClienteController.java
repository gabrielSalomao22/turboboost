package com.example.turboboost.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.models.Cliente;

@Controller
public class ClienteController {

	@RequestMapping(path = "/novo", method = RequestMethod.GET)
	public ModelAndView novoCadastro() {
		return new ModelAndView("cliente/cadastro");
	}
	
	@RequestMapping(path = "/novo", method = RequestMethod.POST)
	public ModelAndView novoCadastro(ClienteDTO clienteDTO) {
		
		System.err.println(clienteDTO.getUsuarioDTO().getEmail());
		System.err.println(clienteDTO.getEnderecoDTO().getCep());
		System.err.println(clienteDTO.getCartaoDTO().getNumeroCartao());
		
		Cliente cliente = new Cliente();
		//cliente = clienteDTO.preencherObjeto(cliente);
		
		
		return new ModelAndView("login");
	}
}
