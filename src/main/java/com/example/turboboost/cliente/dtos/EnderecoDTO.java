package com.example.turboboost.cliente.dtos;

import com.example.turboboost.cliente.models.Cidade;
import com.example.turboboost.cliente.models.Endereco;
import com.example.turboboost.cliente.models.Estado;
import com.example.turboboost.cliente.models.Pais;
import com.example.turboboost.cliente.models.TipoEndereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

	private String nomeEndereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String bairro;
	private String tipoEndereco;
	private String tipoResidencia;
	private String tipoLogradouro;
	private String cidade;
	private String estado;
	private String pais;
	private String observacao;
	
	public Endereco preencherObjeto(Endereco endereco) {
		endereco.setNomeEndereco(this.nomeEndereco);
		endereco.setLogradouro(this.logradouro);
		endereco.setCep(this.cep);
		endereco.setNumero(this.numero);
		endereco.setBairro(this.bairro);
		endereco.setTipoEndereco(this.tipoEndereco == null ? TipoEndereco.AMBOS : TipoEndereco.valueOf(this.tipoEndereco));
		endereco.setTipoLogradouro(this.tipoLogradouro);
		endereco.setTipoResidencia(this.tipoResidencia);
		endereco.setCidade(new Cidade(this.cidade, new Estado(this.estado, new Pais(this.pais))));
		endereco.setObservacao(this.observacao);
		
		return endereco;
	}
	
	public static EnderecoDTO preencherDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		enderecoDTO.setNomeEndereco(endereco.getNomeEndereco());
		enderecoDTO.setLogradouro(endereco.getLogradouro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setTipoEndereco(endereco.getTipoEndereco().toString());
		enderecoDTO.setTipoResidencia(endereco.getTipoResidencia());
		enderecoDTO.setTipoLogradouro(endereco.getTipoLogradouro());
		enderecoDTO.setCidade(endereco.getCidade().getCidade());
		enderecoDTO.setEstado(endereco.getCidade().getEstado().getEstado());
		enderecoDTO.setPais(endereco.getCidade().getEstado().getPais().getPais());
		enderecoDTO.setObservacao(endereco.getObservacao());
		
		return enderecoDTO;
	}
}
