package com.example.turboboost.cliente.models;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
public class Endereco extends EntidadeBasica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "nomeEndereco", nullable = false)
	private String nomeEndereco;
	
	@Basic
	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Basic
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	@Basic
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Basic
	@Column(name = "tipo_logradouro", nullable = false)
	private String tipoLogradouro;
	
	@Basic
	@Column(name = "tipo_residencia", nullable = false)
	private String tipoResidencia;
	
	@Basic
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "tipo_endereco", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "cidade", column = @Column(name = "cidade"))
	})
	private Cidade cidade;
	
	@Basic
	@Column(name = "observacao", nullable = false)
	private String observacao;
}
