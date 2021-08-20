package com.example.turboboost.cliente.dtos;

import java.time.LocalDate;

import com.example.turboboost.cliente.models.Cartao;
import com.example.turboboost.cliente.models.Cliente;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.commons.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

	private String nome;
	private String dataNascimento;
	private String cpf;
	private String genero;
	private String telefone;
	private String tipoTelefone;
	private EnderecoDTO enderecoDTO;
	private UsuarioDTO usuarioDTO;
	private CartaoDTO cartaoDTO;
	
	public Cliente preencherObjeto(Cliente cliente) {		
		cliente.setNome(this.nome);
		cliente.setDataNascimento(LocalDate.parse(this.dataNascimento));
		cliente.setCpf(this.cpf);
		cliente.setGenero(this.genero);
		cliente.setTelefone(this.telefone);
		cliente.setTipoTelefone(this.tipoTelefone);
		cliente.getCartoes().add(cartaoDTO.preencherObjeto(new Cartao()));
		cliente.getEnderecos().add(enderecoDTO.preencherObjeto(new Endereco()));
		cliente.setUsuario(usuarioDTO.preencherObjeto(new Usuario()));
		
		return cliente;
	}
	
}
