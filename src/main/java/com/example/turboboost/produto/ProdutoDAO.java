package com.example.turboboost.produto;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Produto p WHERE p.hash = :hash")
	void deletarByHash(UUID hash);
	
	
}
