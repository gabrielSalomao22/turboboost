package com.example.turboboost.cliente.strategies;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.turboboost.commons.PasswordEncrypt;

public class CriptografaSenhaStrategy {
	
	private PasswordEncrypt passwordEncrypt;
	PasswordEncoder encode = new BCryptPasswordEncoder();

	public String processar(String senha) {
		System.err.println(senha);
		return encode.encode(senha);
	}
}
