package com.example.turboboost.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoDAO  extends JpaRepository<ItemPedido, Long>{

}
