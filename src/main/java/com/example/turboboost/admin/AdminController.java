package com.example.turboboost.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDAO dao;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@RequestMapping(path = "/bemVindoAdmin", method = RequestMethod.GET)
	public ModelAndView bemVindoAdmin() {
		return new ModelAndView("common/admin-layout");
	}
	
	@RequestMapping(path = "/listarClientes", method = RequestMethod.GET)
	public ModelAndView listarClientes() {
		ModelAndView mv = new ModelAndView("admin/listarClientes");
		
		List<Cliente> clientes = clienteDAO.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		
		for(Cliente c : clientes) {
			clientesDTO.add(ClienteDTO.preencherDTO(c));
		}
		
		mv.addObject("clientesDTO", clientesDTO);
		
		return mv;
	}
	
	@RequestMapping(path = "/deletarCliente", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarCliente(String hashCliente) {
		Optional<Cliente> clienteO = clienteDAO.findByHash(UUID.fromString(hashCliente));
		Cliente cliente = clienteO.get();
		
		for(Endereco e : cliente.getEnderecos()) {
			clienteDAO.deleteEndereco(e.getHash());
		}
		
		for(Cartao c : cliente.getCartoes()) {
			clienteDAO.deletarCartao(c.getHash());
		}
		
		
		clienteDAO.deletarCliente(UUID.fromString(hashCliente));
		clienteDAO.deletarUsuario(cliente.getUsuario().getHash());
	}
}
