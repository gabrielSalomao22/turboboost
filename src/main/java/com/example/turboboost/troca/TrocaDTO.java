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
	private String motivoTroca;
	private String codigo;
	private String dataFormatada;
	private Double valorTotal;
	private String status;
	private String cpfCliente;
	private String hashTroca;
	
	
	public static TrocaDTO preencherDTO(Troca troca) {
		TrocaDTO trocaDTO = new TrocaDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		trocaDTO.setHashTroca(troca.getHash().toString());
		trocaDTO.setCodigo(troca.getCodigo());
		trocaDTO.setDataFormatada(troca.getDataTroca().format(formatter));
		trocaDTO.setValorTotal(troca.getValor());
		trocaDTO.setMotivoTroca(troca.getMotivoTroca());
		trocaDTO.setStatus(troca.getStatus().getDescricao());
		
		return trocaDTO;
	}
	
	public static TrocaDTO preencherDTOCliente(Troca troca, String cpfCliente) {
		TrocaDTO trocaDTO = new TrocaDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		trocaDTO.setHashTroca(troca.getHash().toString());
		trocaDTO.setCodigo(troca.getCodigo());
		trocaDTO.setDataFormatada(troca.getDataTroca().format(formatter));
		trocaDTO.setValorTotal(troca.getValor());
		trocaDTO.setMotivoTroca(troca.getMotivoTroca());
		trocaDTO.setStatus(troca.getStatus().getDescricao());
		trocaDTO.setCpfCliente(cpfCliente);
		
		return trocaDTO;
	}
	
	public Troca preencherObjeto(String hashCliente, double valor, List<ItemTroca> itens, String hashPedido) {
		Troca troca = new Troca();
		
		troca.setCodigo(GerarCodigo.gerarCodigo());
		troca.setHashCliente(hashCliente);
		troca.setHashPedido(hashPedido);
		troca.setDataTroca(LocalDate.now());
		troca.setValor(valor);
		troca.setItens(itens);
		troca.setMotivoTroca(this.motivoTroca);
		troca.setStatus(StatusPedido.TROCA);
		
		return troca;
	}
	
}
