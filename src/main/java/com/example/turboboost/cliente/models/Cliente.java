package com.example.turboboost.cliente.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;
import com.example.turboboost.commons.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente extends EntidadeBasica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Basic
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Basic
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Basic
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	@Basic
	@Column(name = "genero", nullable = false)
	private String genero;
	
	@Basic
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Basic
	@Column(name = "tipo_telefone", nullable = false)
	private String tipoTelefone;
	
	@JoinColumn(name = "usuario", nullable = true)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@JoinTable(name = "cliente_cartao", joinColumns = {
			@JoinColumn(name = "cliente_id", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "cartao_id", referencedColumnName = "id")})
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cartao> cartoes = new ArrayList<Cartao>();

}
