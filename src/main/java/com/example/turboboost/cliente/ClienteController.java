package com.example.turboboost.cliente;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dao.ClienteDAO2;
import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.models.Cliente;

@Controller
public class ClienteController {
	
	
	@Autowired
	private ClienteDAO2 dao;
	
	ClienteFacade facade = new ClienteFacade();

	@RequestMapping(path = "/novo", method = RequestMethod.GET)
	public ModelAndView novoCadastro() {
		return new ModelAndView("cliente/cadastro");
	}
	
	@RequestMapping(path = "/novo", method = RequestMethod.POST)
	public ModelAndView novoCadastro(ClienteDTO clienteDTO) {
		
		String msgErro = facade.validarCadastroInicial(clienteDTO);
		if(msgErro != null) {
			ModelAndView mv = new ModelAndView("cliente/cadastro");
			mv.addObject("msgErro", msgErro);
			return mv;
		}
		
		Cliente cliente = new Cliente();
		cliente = clienteDTO.preencherObjeto(cliente);
		dao.save(cliente);
		//clienteDAO.salvar(cliente);
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path = "/bemVindoCliente", method = RequestMethod.GET)
	public ModelAndView bemVindo(Principal principal) {
		
		ModelAndView mv = new ModelAndView("cliente/dados");
		Optional<Cliente> cliente = dao.findByEmail(principal.getName());
		
		mv.addObject("clienteDTO", ClienteDTO.preencherDTO(cliente.get()));
		
		return mv;
	}
	
	@RequestMapping(path = "/editarCadastro", method = RequestMethod.POST)
	public ModelAndView editarCadastro(ClienteDTO clienteDTO) {
		
		Optional<Cliente> clienteOptional = dao.findByHash(clienteDTO.getHashCliente());
		
		String msgErro = facade.validarAlteracaoCadastro(clienteDTO.getCpf());
		if(msgErro != null) {
			ModelAndView mv = new ModelAndView("cliente/dados");
			mv.addObject("msgErro", msgErro);
			return mv;
		}
		
		Cliente cliente = clienteDTO.preencherObjetoEdicao(clienteOptional.get());
		
		dao.saveAndFlush(cliente);
		
		
		return new ModelAndView("redirect:/bemVindoCliente");
	}
	
	@RequestMapping(path = "/alterarSenha", method = RequestMethod.GET)
	public ModelAndView alterarSenha() {
		return new ModelAndView("cliente/senha");
	}
	
	@RequestMapping(path = "/alterarSenha", method = RequestMethod.POST)
	public ModelAndView alterarSenha(String novaSenha, String confirmaSenha, Principal principal) {
		
		Optional<Cliente> clienteOptional = dao.findByEmail(principal.getName());
		
		String retorno = facade.validarAlteracaoSenha(novaSenha, confirmaSenha);
		
		if(retorno.contains(" ")) {
			ModelAndView mv = new ModelAndView("cliente/senha");
			mv.addObject("msgErro", retorno);
			return mv;
		}
		
		Cliente cliente = clienteOptional.get();
		cliente.getUsuario().setSenha(retorno);
		dao.saveAndFlush(cliente);
		
		return new ModelAndView("redirect:/bemVindoCliente");
	}
}
