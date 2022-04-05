package com.example.turboboost.troca;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.turboboost.commons.EntidadeBasica;
import com.example.turboboost.pedido.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "troca")
public class Troca extends EntidadeBasica{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@Column(name = "hashCliente", nullable = false)
	private String hashCliente;
	
	@Column(name = "hashPedido", nullable = false)
	private String hashPedido;
	
	@Column(name = "dataTroca", nullable = false)
	private LocalDate dataTroca;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@JoinTable(name = "troca_itemTroca", joinColumns = {
			@JoinColumn(name = "troca_id", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "itemTroca_id", referencedColumnName = "id")})
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemTroca> itens;
	
	@Column(name = "motivoTroca", nullable = false)
	private String motivoTroca;
	
	@Column(name = "status", nullable = false)
	private StatusPedido status;

}
