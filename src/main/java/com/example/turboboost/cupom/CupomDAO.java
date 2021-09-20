package com.example.turboboost.cupom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomDAO extends JpaRepository<CupomPromocional, Long>{

	@Query("SELECT c FROM CupomPromocional c WHERE c.codigo = :codigo AND c.ativo = true")
	Optional<CupomPromocional> findByCodigo(String codigo);
}
