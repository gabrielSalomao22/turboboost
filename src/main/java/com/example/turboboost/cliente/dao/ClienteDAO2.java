package com.example.turboboost.cliente.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.turboboost.cliente.models.Cliente;

@Repository
public interface ClienteDAO2 extends JpaRepository<Cliente, Long>{

	@Query(value = "Select * from cliente \r\n" + 
			"	JOIN usuario\r\n" + 
			"		on usuario.id = cliente.usuario\r\n" + 
			"	WHERE usuario.email = :email", nativeQuery = true)
	Optional<Cliente> findByEmail(String email);
}
