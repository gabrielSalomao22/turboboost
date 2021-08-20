package com.example.turboboost.cliente.dtos;

import com.example.turboboost.commons.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private String email;
	private String senha;
	private String confirmaSenha;
	
	public Usuario preencherObjeto(Usuario usuario) {
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		
		return usuario;
	}
}
