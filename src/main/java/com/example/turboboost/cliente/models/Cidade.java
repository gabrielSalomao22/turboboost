package com.example.turboboost.cliente.models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {

	private String cidade;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "estado", column = @Column(name = "estado"))
	})
	private Estado estado;
	
}
