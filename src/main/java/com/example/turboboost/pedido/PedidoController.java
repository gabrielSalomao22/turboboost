package com.example.turboboost.pedido;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.dtos.CartaoDTO;
import com.example.turboboost.cliente.dtos.EnderecoDTO;
import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.cupom.CupomDTO;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoController {
	
	@Autowired
	private ClienteDAO clienteDAO;

	
	@RequestMapping(path = "/prosseguir", method = RequestMethod.POST)
	public ModelAndView prosseguir(PedidoDTO pedidoDTO, Principal principal) {
		
		ModelAndView mv = new ModelAndView("pedido/finalizarPedido");
		
		Optional<Cliente> clienteOptional = clienteDAO.findByEmail(principal.getName());
		List<Cartao> cartoes = clienteOptional.get().getCartoes();
		List<Endereco> enderecos = clienteOptional.get().getEnderecos();
		
		List<CartaoDTO> cartoesDTO = new ArrayList<CartaoDTO>();
		List<EnderecoDTO> enderecosDTO = new ArrayList<EnderecoDTO>();
		List<ItemPedidoDTO> itensDTO = new ArrayList<ItemPedidoDTO>();
		List<CupomDTO> cuponsDTO = new ArrayList<CupomDTO>();
		
		for(Cartao c : cartoes) {
			cartoesDTO.add(CartaoDTO.preencherDTO(c));
		}
		
		for(Endereco e : enderecos) {
			enderecosDTO.add(EnderecoDTO.preencherDTO(e));
		}
		
		for(int i = 0; i < pedidoDTO.getHashProduto().length; i++) {
			itensDTO.add(ItemPedidoDTO.preencherDTO(pedidoDTO.getHashProduto()[i], pedidoDTO.getQuantidadeItem()[i]));
		}
		
		/*
		 * for(int i = 0; i < pedidoDTO.getCupomCliente().length; i++) {
		 * cuponsDTO.add(CupomDTO.preencherDTO(pedidoDTO.getCupomCliente()[i])); }
		 */
		
		mv.addObject("pedidoDTO", pedidoDTO);
		mv.addObject("itensDTO", itensDTO);
		mv.addObject("cartoesDTO", cartoesDTO);
		mv.addObject("enderecosDTO", enderecosDTO);
		
		return mv;
	}
	
	
}
