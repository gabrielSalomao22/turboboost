package com.example.turboboost.troca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.turboboost.cupom.GerarCodigo;
import com.example.turboboost.pedido.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrocaDTO {

	private String hashPedido;
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
	
	public Troca preencherObjeto(String hashCliente, double valor, List<ItemTroca> itens) {
		Troca troca = new Troca();
		
		troca.setCodigo(GerarCodigo.gerarCodigo());
		troca.setHashCliente(hashCliente);
		troca.setDataTroca(LocalDate.now());
		troca.setValor(valor);
		troca.setItens(itens);
		troca.setStatus(StatusPedido.PROCESSAMENTO);
		
		return troca;
	}
	
}
