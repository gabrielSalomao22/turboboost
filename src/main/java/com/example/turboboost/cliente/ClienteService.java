package com.example.turboboost.cliente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboboost.cliente.dao.ClienteDAO;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Ranking;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO dao;
	
	public void alterarPontuacao(String hashCliente, int pontuacao) {
		Optional<Cliente> clienteO = dao.findByHash(UUID.fromString(hashCliente));
		Cliente cliente = clienteO.get();
		
		if(cliente.getPontuacao() == 0) {
			if(pontuacao < 0) {
				cliente.setPontuacao(0);
				
			}else {
				cliente.setPontuacao(cliente.getPontuacao() + pontuacao);
			}
			
		}else if(cliente.getPontuacao() > 0 && cliente.getPontuacao() < 100) {
			if(pontuacao < 0) {
				System.err.println("ta aki");
				System.err.println(cliente.getPontuacao() + pontuacao);
				cliente.setPontuacao(cliente.getPontuacao() + pontuacao);
				
			}else {
				cliente.setPontuacao(cliente.getPontuacao() + pontuacao);
			}
						
		}else if(cliente.getPontuacao() == 100) {
			if(pontuacao > 0) {
				cliente.setPontuacao(100);
				
			}else {
				cliente.setPontuacao(cliente.getPontuacao() + pontuacao);
			}
		}
		
		dao.saveAndFlush(cliente);
		alterarRank(cliente.getHash());
	}
	
	private void alterarRank(UUID hashCliente) {
		Optional<Cliente> clienteO = dao.findByHash(hashCliente);
		Cliente cliente = clienteO.get();
		
		if(cliente.getPontuacao() <=20) {
			cliente.setRanking(Ranking.MUITO_BAIXO);
			
		}else if(cliente.getPontuacao() > 20 && cliente.getPontuacao() <= 40) {
			cliente.setRanking(Ranking.BAIXO);
			
		}else if(cliente.getPontuacao() > 40 && cliente.getPontuacao() <= 60) {
			cliente.setRanking(Ranking.REGULAR);
			
		}else if(cliente.getPontuacao() > 60 && cliente.getPontuacao() <= 80) {
			cliente.setRanking(Ranking.ALTO);
			
		}else {
			cliente.setRanking(Ranking.MUITO_ALTO);
		}
		
		dao.saveAndFlush(cliente);
	}
}
