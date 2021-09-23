package com.example.turboboost.pedido;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.cupom.CupomDAO;
import com.example.turboboost.cupom.CupomPromocional;
import com.example.turboboost.produto.ProdutoDAO;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO dao;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private CupomDAO cupomDAO;
	
	
	public void novo(PedidoDTO pedidoDTO, Principal principal) {
		Optional<Cliente> clienteOptional = clienteDAO.findByEmail(principal.getName());
		Optional<Endereco> enderecoOptional = clienteDAO.findEnderecoByHash(pedidoDTO.getHashEndereco());
		Optional<Cartao> cartaoOptional = clienteDAO.findCartaoByHash(pedidoDTO.getHashCartao());
		List<CupomPromocional> cuponsUtilizado = new ArrayList<CupomPromocional>();
		
		if(pedidoDTO.getCupomCliente() != null) {
			for(int i = 0; i < pedidoDTO.getCupomCliente().length; i++) {
				Optional<CupomPromocional> cupomOptional = cupomDAO.findByHash(pedidoDTO.getCupomCliente()[i]);
				cuponsUtilizado.add(cupomOptional.get());
			}
			
		}
		
		if(pedidoDTO.getCupomPromocional() != null) {
			cuponsUtilizado.add(cupomDAO.findByHash(pedidoDTO.getCupomPromocional()).get());
			
		}
		
		Pedido pedido = pedidoDTO.preencherObjeto(pedidoDTO, clienteOptional.get(), produtoDAO, enderecoOptional.get(), cartaoOptional.get(), cuponsUtilizado);
		
		dao.save(pedido);
	}
	
	public List<PedidoDTO> listarPedidos(){
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		List<Pedido> pedidos = dao.findAll();
		
		for(Pedido p : pedidos) {
			Optional<Cliente> clienteOptional = clienteDAO.findByHash(UUID.fromString(p.getHashCliente()));
			
			pedidosDTO.add(PedidoDTO.preencherDTO(p, clienteOptional.get()));
		}
		
		return pedidosDTO;
		
	}
	
	public List<PedidoDTO> pedidosCliente(Principal principal){
		Optional<Cliente> clienteOptional = clienteDAO.findByEmail(principal.getName());
		List<Pedido> pedidos = dao.findByClienteHash(clienteOptional.get().getHash().toString());
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		
		for(Pedido p : pedidos) {
			pedidosDTO.add(PedidoDTO.preencherDTO(p));
		}
		
		
		return pedidosDTO;
	}
}
