package com.example.turboboost.troca;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrocaDAO extends JpaRepository<Troca, Long>{

	@Query("SELECT t FROM Troca t WHERE t.hashCliente = :hashCliente")
	List<Troca> findByHashCliente(String hashCliente);
	
	@Query("SELECT t FROM Troca t WHERE t.hash = :hash")
	Optional<Troca> findByHash(UUID hash);
}
