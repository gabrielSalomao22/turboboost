package com.example.turboboost.cupom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cupom_promocional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CupomPromocional extends EntidadeBasica{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@Column(name = "porcentagem", nullable = true)
	private Integer porcentagem;
	
	@Column(name = "valor", nullable = true)
	private Double valor;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@Column(name = "hashCliente", nullable = true)
	private String hashCliente;

}
