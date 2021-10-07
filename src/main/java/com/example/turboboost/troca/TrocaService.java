package com.example.turboboost.troca;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrocaService {

	@Autowired
	private TrocaDAO dao;
	
	public void novaTroca(TrocaDTO trocaDTO, Principal principal) {
		
	}
}
