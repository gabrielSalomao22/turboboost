package com.example.turboboost.cupom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService {

	@Autowired
	private CupomDAO dao;
	
	public String gerarCupomPromocional() {
		String codigo = gerarCupomPromocional();
		
		System.err.println(codigo);
		
		return codigo;
	}
	
	public List<CupomDTO> listarCupons(){
		List<CupomPromocional> cupons = dao.findAll();
		List<CupomDTO> cuponsDTO = new ArrayList<CupomDTO>();
		
		for(CupomPromocional c : cupons) {
			cuponsDTO.add(CupomDTO.preencherDTO(c));
		}
		
		return cuponsDTO;
		
	}
	
	public void novo(CupomDTO cupomDTO) {
		CupomPromocional cupom = new CupomPromocional();
		cupom.setAtivo(true);
		
		cupom = cupomDTO.preencherObjetoNovo(cupom);
		
		System.err.println(cupom.getCodigo());
		
		dao.saveAndFlush(cupom);
	}
}
