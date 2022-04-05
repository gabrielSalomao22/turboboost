package com.example.turboboost.produto;

import java.util.List;
import java.util.Optional;
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
	
	@Query("SELECT p from Produto p WHERE p.status = 'Ativo' AND p.estoque > 0")
	List<Produto> buscarParaVenda();
	
	@Query("SELECT p from Produto p WHERE p.hash = :hashProduto")
	Optional<Produto> findByHash(UUID hashProduto);
	
}
