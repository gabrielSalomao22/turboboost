package com.example.turboboost.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.turboboost.commons.Usuario;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Optional<Usuario> usuario = usuarioDAO.findByEmail(email);
		 
		 if(!usuario.isPresent()) {
			 throw new UsernameNotFoundException("Não foi encontrada nenhum cadastro com este email!");
		 }
		 
		 return new UsuarioDetails(usuario.get());
	}

}
