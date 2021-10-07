package com.example.turboboost.troca;

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
@Table(name = "item_troca")
public class ItemTroca extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "hashProduto", nullable = false)
	private String hashProduto;
	
	@Column(name = "valorUnitario", nullable = false)
	private Double valorUnitario;

}
