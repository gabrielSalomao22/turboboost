package com.example.turboboost.cliente.dao;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;

@Repository
public interface ClienteDAO2 extends JpaRepository<Cliente, Long>{

	@Query(value = "Select * from cliente \r\n" + 
			"	JOIN usuario\r\n" + 
			"		on usuario.id = cliente.usuario\r\n" + 
			"	WHERE usuario.email = :email", nativeQuery = true)
	Optional<Cliente> findByEmail(String email);
	
	@Query("SELECT c FROM Cliente c WHERE c.hash = :hash")
	Optional<Cliente> findByHash(UUID hash);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Endereco e WHERE e.hash = :hash")
	void deleteEndereco(UUID hash);
	
	@Query("SELECT e FROM Endereco e WHERE e.hash = :hash")
	Optional<Endereco> findEnderecoByHash(UUID hash);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Cartao c WHERE c.hash = :hash")
	void deletarCartao(UUID hash);
	
	@Query("SELECT c FROM Cartao c WHERE c.hash = :hash")
	Optional<Cartao> findCartaoByHash(UUID hash);
}

