package com.example.turboboost.troca;

import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrocaDTO {

	private String[] hashProduto;
	private String codigo;
	private String dataFormatada;
	private Double valorTotal;
	private String status;
	
	
	public static TrocaDTO preencherDTO(Troca troca) {
		TrocaDTO trocaDTO = new TrocaDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		trocaDTO.setCodigo(troca.getCodigo());
		trocaDTO.setDataFormatada(troca.getDataTroca().format(formatter));
		trocaDTO.setValorTotal(troca.getValor());
		trocaDTO.setStatus(troca.getStatus().getDescricao());
		
		return trocaDTO;
	}
	
}
