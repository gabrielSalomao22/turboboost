package com.example.turboboost.commons;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.example.turboboost.config.ConversorUUIDString;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Access(AccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
public abstract class EntidadeBasica implements Serializable{


	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	@Column(name = "data_criacao", nullable = false, updatable = false)
	protected LocalDateTime dataCriacao;
	
	@Basic
	@Column(name = "habilitado", nullable = false)
	protected boolean habilitado = true;
	
	@Basic
	@Column(name = "hash", nullable = false, unique = true, updatable = false)
	@Convert(converter = ConversorUUIDString.class)
	protected UUID hash;
	
	
	@Access(AccessType.PROPERTY)
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_entidade")
	@SequenceGenerator(name = "seq_entidade", initialValue = 50, allocationSize = 1)
	@Id
	public Long getId() {
		return this.id;
	}
	
	public EntidadeBasica() {
		this.hash = UUID.randomUUID();
		this.dataCriacao = LocalDateTime.now();
		this.habilitado = true;
	}

}
