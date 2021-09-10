package com.example.turboboost.cupom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CupomDTO {

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
		
		cupomDTO.setCodigo(cupom.getCodigo());
		cupomDTO.setValor(cupom.getValor());
		cupomDTO.setPorcentagem(cupom.getPorcentagem());
		cupomDTO.setAtivo(cupom.isAtivo());
		
		return cupomDTO;
	}
	
}
