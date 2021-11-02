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
		List<DadosDTO> dados = new ArrayList<DadosDTO>();
		
		graficoDTO.setQntDatas(datasGeradas.size());
		
		for(Date d : datasGeradas) {
			String dataString = sdf.format(d);
			
			DadosDTO dadosDTO = new DadosDTO();
			dadosDTO.setData(dataString);
			
			List<Pedido> pedidos = pedidoDAO.findByData(LocalDate.parse(dataString));
			Map<String, Integer> mapa = new HashMap<String, Integer>();
			
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
		
		graficoDTO.setDados(dados);
		
		
		
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
