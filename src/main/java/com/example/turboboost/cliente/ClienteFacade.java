package com.example.turboboost.cliente;

import com.example.turboboost.cliente.dtos.ClienteDTO;
import com.example.turboboost.cliente.strategies.CriptografaSenhaStrategy;
import com.example.turboboost.cliente.strategies.ValidarCpfStrategy;
import com.example.turboboost.cliente.strategies.ValidarSenhaStrategy;

public class ClienteFacade {
	
	ValidarSenhaStrategy validarSenha = new ValidarSenhaStrategy();
	CriptografaSenhaStrategy criptogradaSenha = new CriptografaSenhaStrategy();
	ValidarCpfStrategy validarCpf = new ValidarCpfStrategy();
	
	public String validarCadastroInicial(ClienteDTO cliente) {
		String msg;
		
		msg = validarSenha.processar(cliente.getUsuarioDTO().getSenha(), cliente.getUsuarioDTO().getConfirmaSenha());
		if(msg != null) {
			return msg;
		}
		
		cliente.getUsuarioDTO().setSenha(criptogradaSenha.processar(cliente.getUsuarioDTO().getSenha()));
		
		msg = validarCpf.processar(cliente.getCpf());
		if(msg != null) {
			return msg;
		}
		
		
		
		
		return null;
	}
	
	public String validarAlteracaoSenha(String novaSenha, String confirmaNovaSenha) {
		String msg;
		
		msg = validarSenha.processar(novaSenha, confirmaNovaSenha);
		if(msg != null) {
			return msg;
		}
		
		criptogradaSenha.processar(novaSenha);
		
		
		return null;
	}
}
