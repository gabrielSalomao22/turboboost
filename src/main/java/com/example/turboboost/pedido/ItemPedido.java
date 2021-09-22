package com.example.turboboost.pedido;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "hashProduto", nullable = false)
	private UUID hashProduto;
	
	@Column(name = "quantidade_item", nullable = false)
	private int quantidadeItem;
}
