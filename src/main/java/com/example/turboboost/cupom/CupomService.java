package com.example.turboboost.cupom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	
	public CupomDTO aplicarCupom(String codigo) {
		Optional<CupomPromocional> cupomOptional = dao.findByCodigo(codigo);
		
		if(cupomOptional.isPresent()) {			
			return CupomDTO.preencherDTO(cupomOptional.get());
			
		}else {
			return null;
		}
		
	}
	
	public void gerarCupomTroca(String hashCliente, double valor) {
		CupomPromocional cupom = new CupomPromocional();
		cupom.setAtivo(true);
		
		cupom = CupomDTO.preencherCupomTroca(cupom, hashCliente, valor);
		
		dao.saveAndFlush(cupom);
	}
	
	public List<CupomDTO> listarCuponsCliente(UUID hashCliente) {
		List<CupomPromocional> cupons = dao.findCuponsCliente(hashCliente.toString());
		List<CupomDTO> cuponsDTO = new ArrayList<CupomDTO>();
		
		for(CupomPromocional c : cupons) {
			cuponsDTO.add(CupomDTO.preencherDTO(c));
		}
		
		return cuponsDTO;
		
	}
}
