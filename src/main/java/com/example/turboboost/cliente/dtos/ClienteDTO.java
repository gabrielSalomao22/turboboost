package com.example.turboboost.cliente.dtos;

import java.time.LocalDate;
import java.util.List;

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
	private List<CartaoDTO> cartoesDTO;
	private List<EnderecoDTO> enderecosDTO;
	
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
	
	public static ClienteDTO preencherDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setUsuarioDTO(UsuarioDTO.preencherDTO(cliente.getUsuario()));
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setDataNascimento(cliente.getDataNascimento().toString());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setGenero(cliente.getGenero());
		clienteDTO.setTelefone(cliente.getTelefone());
		clienteDTO.setTipoTelefone(cliente.getTipoTelefone());
		
		return clienteDTO;
	}
	
}
