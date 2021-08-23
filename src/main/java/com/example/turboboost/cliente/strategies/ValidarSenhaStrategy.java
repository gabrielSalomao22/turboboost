package com.example.turboboost.cliente.strategies;

import com.example.turboboost.cliente.dtos.ClienteDTO;

public class ValidarSenhaStrategy{

	public String processar(String senha, String confirmaSenha) {
		
		if(!senha.equals(confirmaSenha)) {
			return "Senhas estão diferentes!";
		}
		
		if(!(senha.length() >= 8)) {
			return "A senha não atente ao número minimo de caracteres!";
		}
		
		if(!senha.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$")) {
			return "A senha não é forte!";
		}
		
		return null;
	}

}
