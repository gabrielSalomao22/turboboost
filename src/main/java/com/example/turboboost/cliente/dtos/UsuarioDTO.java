package com.example.turboboost.cliente.dtos;

import com.example.turboboost.commons.TipoUsuario;
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
		usuario.setRole(TipoUsuario.CLIENTE);
		
		return usuario;
	}
	
	public static UsuarioDTO preencherDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setEmail(usuario.getEmail());
		
		return usuarioDTO;
	}
}
