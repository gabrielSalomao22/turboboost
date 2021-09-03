package com.example.turboboost.commons;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBasica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "email", nullable = false)
	private String email;

	@Basic
	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	protected TipoUsuario role;

	
	/*
	 * @Override
	 * 
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
	 * 
	 * @SequenceGenerator(name = "seq_usuario", initialValue = 50, allocationSize =
	 * 1) public Long getId() { return super.getId(); }
	 */
	 

}
