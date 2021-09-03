package com.example.turboboost.cliente.models;

import java.io.Serializable;

import javax.persistence.Basic;
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
@Table(name = "cartao")
public class Cartao extends EntidadeBasica implements Serializable{
	 
	 
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "numero_cartao", nullable = false)
	private String numeroCartao;
	
	@Basic
	@Column(name = "nome_impresso", nullable = false)
	private String nomeImpresso;
	
	@Basic
	@Column(name = "cvv", nullable = false)
	private String cvv;
	
	@Basic
	@Column(name = "bandeira", nullable = false)
	private String bandeira;
	
}
