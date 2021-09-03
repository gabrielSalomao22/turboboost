package com.example.turboboost.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.models.Cliente;

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
}
