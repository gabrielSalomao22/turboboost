package com.example.turboboost.troca;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboboost.cliente.ClienteService;
import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cupom.CupomService;
import com.example.turboboost.pedido.PedidoService;
import com.example.turboboost.pedido.StatusPedido;
import com.example.turboboost.produto.Produto;
import com.example.turboboost.produto.ProdutoDAO;

@Service
public class TrocaService {

	@Autowired
	private TrocaDAO dao;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private CupomService cupomService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public void novaTroca(TrocaDTO trocaDTO, Principal principal) {
		Optional<Cliente> clienteOptional = clienteDAO.findByEmail(principal.getName());
		List<ItemTroca> itens = new ArrayList<ItemTroca>();
		double valorCupom = 0;
		
		for(String s : trocaDTO.getHashProduto()) {
			Optional<Produto> produtoOptional = produtoDAO.findByHash(UUID.fromString(s));
			
			itens.add(new ItemTroca(produtoOptional.get().getHash().toString(), produtoOptional.get().getPreco()));
			valorCupom += produtoOptional.get().getPreco();
		}
		
		Troca troca = trocaDTO.preencherObjeto(clienteOptional.get().getHash().toString(), valorCupom, itens, trocaDTO.getHashPedido());
		
		dao.saveAndFlush(troca);
		
		pedidoService.solicitacaoTroca(trocaDTO.getHashPedido());
		
		//cupomService.gerarCupomTroca(clienteOptional.get().getHash().toString(), valorCupom);
		
		
	}
	
	public List<TrocaDTO> listarTrocaCliente(UUID hashCliente){
		List<Troca> trocas = dao.findByHashCliente(hashCliente.toString());
		List<TrocaDTO> trocasDTO = new ArrayList<TrocaDTO>();
		
		for(Troca t : trocas) {
			trocasDTO.add(TrocaDTO.preencherDTO(t));
		}
		
		return trocasDTO;
	}
	
	public List<TrocaDTO> listarTrocas(){
		List<Troca> trocas = dao.findAll();
		List<TrocaDTO> trocasDTO = new ArrayList<TrocaDTO>();
		
		for(Troca t : trocas) {
			Optional<Cliente> clienteOptional = clienteDAO.findByHash(UUID.fromString(t.getHashCliente()));
			trocasDTO.add(TrocaDTO.preencherDTOCliente(t, clienteOptional.get().getCpf()));
		}
		
		return trocasDTO;
	}
	
	public void alterarStatus(String hashTroca) {
		Optional<Troca> trocaOptional = dao.findByHash(UUID.fromString(hashTroca));
		
		Troca troca = trocaOptional.get();
		
		if(troca.getStatus().equals(StatusPedido.TROCA_ACEITA)) {
			troca.setStatus(StatusPedido.PROCESSAMENTO);
			
		}else if(troca.getStatus().equals(StatusPedido.PROCESSAMENTO)) {
			troca.setStatus(StatusPedido.AGUARDANDO);
			
		}else if(troca.getStatus().equals(StatusPedido.AGUARDANDO)) {
			troca.setStatus(StatusPedido.TRANSPORTE);
			
		}else if(troca.getStatus().equals(StatusPedido.TRANSPORTE)) {
			troca.setStatus(StatusPedido.RECEBIDO);
			
		}
		
		dao.saveAndFlush(troca);
	}
	
	public void aceitarRecusar(String hashTroca, boolean status) {
		
		Optional<Troca> trocaOptional = dao.findByHash(UUID.fromString(hashTroca));
		Troca troca = trocaOptional.get();
		
		if(status) {
			troca.setStatus(StatusPedido.TROCA_ACEITA);
			cupomService.gerarCupomTroca(troca.getHashCliente(), troca.getValor());
			clienteService.alterarPontuacao(troca.getHashCliente(), 1);
			
		}else {
			troca.setStatus(StatusPedido.TROCA_RECUSADA);
			clienteService.alterarPontuacao(troca.getHashCliente(), -1);
		}
		
		dao.saveAndFlush(troca);
		
	}
}
