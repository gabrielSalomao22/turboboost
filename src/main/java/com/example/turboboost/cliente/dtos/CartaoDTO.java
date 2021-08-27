package com.example.turboboost.cliente.dtos;

import java.util.UUID;

import com.example.turboboost.cliente.models.Cartao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDTO {

	private String numeroCartao;
	private String nomeImpresso;
	private String cvv;
	private String bandeira;
	
	private UUID hashCartao;
	
	public Cartao preencherObjeto(Cartao cartao) {
		cartao.setNumeroCartao(this.numeroCartao);
		cartao.setNomeImpresso(this.nomeImpresso);
		cartao.setCvv(this.cvv);
		cartao.setBandeira(this.bandeira);
		
		return cartao;
	}
	
	public static CartaoDTO preencherDTO(Cartao cartao) {
		CartaoDTO cartaoDTO = new CartaoDTO();
		
		cartaoDTO.setHashCartao(cartao.getHash());
		cartaoDTO.setNumeroCartao(cartao.getNumeroCartao());
		cartaoDTO.setNomeImpresso(cartao.getNomeImpresso());
		cartaoDTO.setCvv(cartao.getCvv());
		cartaoDTO.setBandeira(cartao.getBandeira());
		
		return cartaoDTO;
	}
}
