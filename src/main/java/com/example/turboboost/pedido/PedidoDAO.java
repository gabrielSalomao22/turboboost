package com.example.turboboost.pedido;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p WHERE p.hashCliente = :hash")
	List<Pedido> findByClienteHash(String hash);
}
