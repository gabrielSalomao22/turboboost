package com.example.turboboost.cupom;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CupomDTO {

	private UUID hashCupom;
	private String codigo;
	private Double valor;
	private Integer porcentagem;
	private boolean ativo;
	
	public CupomPromocional preencherObjeto(CupomPromocional cupom) {
		cupom.setCodigo(this.codigo);
		cupom.setValor(this.valor);
		cupom.setPorcentagem(this.porcentagem);
		cupom.setAtivo(this.ativo);
		
		return cupom;
	}
	
	public static CupomDTO preencherDTO(CupomPromocional cupom) {
		CupomDTO cupomDTO = new CupomDTO();
		
		cupomDTO.setHashCupom(cupom.getHash());
		cupomDTO.setCodigo(cupom.getCodigo());
		cupomDTO.setValor(cupom.getValor());
		cupomDTO.setPorcentagem(cupom.getPorcentagem());
		cupomDTO.setAtivo(cupom.isAtivo());
		
		return cupomDTO;
	}
	
	public CupomPromocional preencherObjetoNovo(CupomPromocional cupom) {
		cupom.setCodigo(this.codigo);
		cupom.setPorcentagem(this.porcentagem);
		cupom.setValor(this.valor);
		
		return cupom;
	}
	
	public static CupomPromocional preencherCupomTroca(CupomPromocional cupom, String hashCliente, double valor) {
		cupom.setCodigo("CP" + GerarCodigo.gerarCodigo());
		cupom.setValor(valor);
		cupom.setHashCliente(hashCliente);
		
		return cupom;
	}
	
}
