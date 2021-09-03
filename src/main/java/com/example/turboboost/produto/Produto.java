package com.example.turboboost.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeBasica{

	private static final long serialVersionUID = 1L;

	@Column(name = "sku", nullable = false)
	private String sku;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "estoque", nullable = false)
	private Integer estoque;
	
	@Column(name = "imagem", nullable = false)
	private String imagem;
	
	@Column(name = "status", nullable = false)
	private String status;
}
