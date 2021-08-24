package com.example.turboboost.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboboost.commons.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
}
