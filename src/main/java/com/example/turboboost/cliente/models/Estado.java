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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

	private String estado;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "pais", column = @Column(name = "pais"))
	})
	private Pais pais;
}
