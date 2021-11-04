package com.example.turboboost.grafico;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboboost.pedido.ItemPedido;
import com.example.turboboost.pedido.Pedido;
import com.example.turboboost.pedido.PedidoDAO;
import com.example.turboboost.produto.Produto;
import com.example.turboboost.produto.ProdutoDAO;

@Service
public class GraficoService {
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;

	
	public GraficoDTO gerarDados(LocalDate dataInicio, LocalDate dataFim){
		GraficoDTO graficoDTO = new GraficoDTO();
		List<Date> datasGeradas = gerarDatas(dataInicio, dataFim);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		List<DadosDTO> dados = new ArrayList<DadosDTO>();
		
		graficoDTO.setQntDatas(datasGeradas.size());
		
		for(Date d : datasGeradas) {
			String dataString = sdf.format(d);
			
			DadosDTO dadosDTO = new DadosDTO();
			dadosDTO.setData(dataString);
			
			List<Pedido> pedidos = pedidoDAO.findByData(LocalDate.parse(dataString));
			Map<String, Integer> mapa = new HashMap<String, Integer>();
			
			System.err.println(pedidos.size());
			
			for(Pedido p : pedidos) {
				
				
				
				for(ItemPedido ip : p.getItens()) {
					mapa.put(ip.getHashProduto(), mapa.getOrDefault(ip.getHashProduto(), 0) + ip.getQuantidadeItem());
				}
				
			}
			
			
			List<ItemGraficoDTO> itens = new ArrayList<ItemGraficoDTO>();
			
			for(Map.Entry<String, Integer> entry : mapa.entrySet()) {
				Optional<Produto> produtoO = produtoDAO.findByHash(UUID.fromString(entry.getKey()));
				
				ItemGraficoDTO item = new ItemGraficoDTO();
				item.setNomeProduto(produtoO.get().getNome());
				item.setQtdProduto(entry.getValue());
				
				itens.add(item);
			}
			
			dadosDTO.setItens(itens);
			dados.add(dadosDTO);
			
		}
		
		List<ProdutoQTD> produtos = new ArrayList<ProdutoQTD>();
		
		for(DadosDTO d : dados) {
			
			
			
			for(int i = 0; i< d.getItens().size(); i++) {
				ProdutoQTD p = new ProdutoQTD();
				int index = 0;
				boolean flg = false;
				
				for(int y = 0; y < produtos.size(); y++) {
					
					
					if(produtos.get(y).getNomeProduto().equals(d.getItens().get(i).getNomeProduto())) {
						flg = true;
						
						index = produtos.indexOf(produtos.get(y));
					}
					
					
				}
				
				
				if(flg){
					flg = false;					
					
					produtos.get(index).getQtd().add(d.getItens().get(i).getQtdProduto());
					System.err.println("ta no if");
					
					
					
					
					
				}else {
					System.err.println("ta no else");
					p.setNomeProduto(d.getItens().get(i).getNomeProduto());
					p.getQtd().add(d.getItens().get(i).getQtdProduto());
					produtos.add(p);
				}
				
				
				
			}
			
		}
		
		for(ProdutoQTD p : produtos) {
			System.err.println(p.getNomeProduto());
			System.err.println(p.getQtd().toString());
			System.err.println("--------------");
		}
		
		List<String> datasFormatadas = new ArrayList<String>();
		
		for(Date d : datasGeradas) {
			datasFormatadas.add(sdf2.format(d));
		}
		
		graficoDTO.setProdutos(produtos);
		graficoDTO.setDatas(datasFormatadas);
		graficoDTO.setDados(dados);
		
		
		
		
		
		return graficoDTO;
	}
	
	public GraficoDTO gerarGrafico2(LocalDate dataInicio, LocalDate dataFim) {
		GraficoDTO graficoDTO = new GraficoDTO();
		List<Date> datasGeradas = gerarDatas(dataInicio, dataFim);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		int datasPercorridas = 0;
		List<ProdutoQTD> produtos = new ArrayList<ProdutoQTD>();
		
		for(Date d : datasGeradas) {
			datasPercorridas += 1;
			String dataString = sdf.format(d);
			
			DadosDTO dadosDTO = new DadosDTO();
			dadosDTO.setData(dataString);
			
			List<Pedido> pedidos = pedidoDAO.findByData(LocalDate.parse(dataString));
			Map<String, Integer> mapa = new HashMap<String, Integer>();
			
			System.err.println(pedidos.size());
			
			for(Pedido p : pedidos) {
				
				
				
				for(ItemPedido ip : p.getItens()) {
					mapa.put(ip.getHashProduto(), mapa.getOrDefault(ip.getHashProduto(), 0) + ip.getQuantidadeItem());
				}
				
			}
			
			
			List<ItemGraficoDTO> itens = new ArrayList<ItemGraficoDTO>();
			
			for(Map.Entry<String, Integer> entry : mapa.entrySet()) {
				Optional<Produto> produtoO = produtoDAO.findByHash(UUID.fromString(entry.getKey()));
				
				ItemGraficoDTO item = new ItemGraficoDTO();
				item.setNomeProduto(produtoO.get().getNome());
				item.setQtdProduto(entry.getValue());
				
				itens.add(item);
			}
			
			for(ItemGraficoDTO i : itens) {
				ProdutoQTD p = new ProdutoQTD();
				int index = 0;
				boolean flg = false;
				List<Integer> indexModificados = new ArrayList<Integer>();
				
				for(int y = 0; y < produtos.size(); y++) {
					
					
					if(produtos.get(y).getNomeProduto().equals(i.getNomeProduto())) {
						flg = true;
						
						index = produtos.indexOf(produtos.get(y));
					}
					
					
				}
				
				
				if(flg){
					flg = false;					
					
					produtos.get(index).getQtd().add(i.getQtdProduto());
					
					indexModificados.add(index);
					
					
					
					
				}else {
					System.err.println("ta no else");
					
					for(int z = 0; z<= datasPercorridas; z++) {
						p.getQtd().add(z, 0);
					}
					
					p.setNomeProduto(i.getNomeProduto());
					p.getQtd().add(i.getQtdProduto());
					produtos.add(p);
				}
				
				for(ProdutoQTD pq : produtos) {
					
					int indexAtual = produtos.indexOf(pq);
					
					if(!indexModificados.contains(indexAtual)) {
						pq.getQtd().add(0);
					}
					
				}
				
				
			}
			
		}
		
		List<String> datasFormatadas = new ArrayList<String>();
		
		for(Date d : datasGeradas) {
			datasFormatadas.add(sdf2.format(d));
		}
		
		graficoDTO.setDatas(datasFormatadas);
		graficoDTO.setProdutos(produtos);
		
		return graficoDTO;
		
	}
	

	private List<Date> gerarDatas(LocalDate dataInicio, LocalDate dataFim){
		List<Date> datasGeradas = new ArrayList<Date>();
		
		Calendar c = Calendar.getInstance();
		c.set(dataInicio.getYear(), dataInicio.getMonthValue()-1, dataInicio.getDayOfMonth());
		
		Long p2 = ChronoUnit.DAYS.between(dataInicio, dataFim);
		
		for(int i = 0; i<=p2; i++) {
			if(i == 0) {
				c.add(Calendar.DAY_OF_MONTH, 0);
				
			}else {
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			Date date = c.getTime();
			datasGeradas.add(date);
		}
		
		return datasGeradas;
	}
}
