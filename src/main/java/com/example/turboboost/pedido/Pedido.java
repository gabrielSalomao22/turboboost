package com.example.turboboost.pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.commons.EntidadeBasica;
import com.example.turboboost.cupom.CupomPromocional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBasica{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@Column(name = "dataPedido", nullable = false)
	private LocalDate dataPedido;
	
	@Column(name = "hashCliente", nullable = false)
	private String hashCliente;
	
	@Column(name = "valorTotal", nullable = false)
	private Double valorTotal;
	
	@JoinTable(name = "pedido_cupom", joinColumns = {
			@JoinColumn(name = "pedido_id", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "cupom_id", referencedColumnName = "id")})
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CupomPromocional> cuponsUtilizados;
	
	@JoinTable(name = "pedido_itemPedido", joinColumns = {
			@JoinColumn(name = "pedido_id", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "itemPedido_id", referencedColumnName = "id")})
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemPedido> itens;
	
	@Column(name = "hashEnderecoSelecionado", nullable = false)
	private String hashEndereco;
	
	@Column(name = "hashCartaoSelecionado", nullable = false)
	private String hashCartao;
	
	@Column(name = "statusPedido", nullable = false)
	private StatusPedido status;

}
