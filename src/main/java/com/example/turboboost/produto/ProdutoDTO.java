package com.example.turboboost.produto;

import java.io.IOException;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.turboboost.commons.FileUpload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private UUID hashProduto;
	private String sku;
	private String nome;
	private double precoCusto;
	private double preco;
	private int estoque;
	private String imagem;
	private String status;
	private int qtdDisponivel;
	private String categoriaString;
	private String motivoInativacao;
	private Categoria categoria;
	
	//private MultipartFile imagemUpload;
	
	public static ProdutoDTO preencherDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		produtoDTO.setHashProduto(produto.getHash());
		produtoDTO.setPrecoCusto(produto.getPrecoCusto());
		produtoDTO.setSku(produto.getSku());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setEstoque(produto.getEstoque());
		produtoDTO.setQtdDisponivel(produto.getEstoque());
		produtoDTO.setImagem("/imagens-produtos/" + produto.getImagem());
		produtoDTO.setStatus(produto.isHabilitado() ? "Ativo" : "Inativo");
		produtoDTO.setMotivoInativacao(produto.getMotivoInativacao());
		produtoDTO.setCategoriaString(produto.getCategoria().getDescricao());
		produtoDTO.setCategoria(produto.getCategoria());
		
		return produtoDTO;
	}
	
	public Produto preencherObjeto(Produto produto, MultipartFile file) throws IOException{
		
		
		produto.setSku(this.sku);
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		produto.setEstoque(this.estoque);
		produto.setStatus("Ativo");
		produto.setPrecoCusto(this.precoCusto);
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		produto.setImagem(fileName);
		
		String uploadDir = "imagens-produtos/";
		
		FileUpload.saveFile(uploadDir, fileName, file);
		produto.setCategoria(this.categoria);
		
		return produto;
	}
	
	public Produto preencherObjetoEditar(Produto produto) {
		produto.setPrecoCusto(this.precoCusto);
		produto.setPreco(this.preco);
		produto.setNome(this.nome);
		produto.setEstoque(this.estoque);
		produto.setCategoria(this.categoria);
		
		return produto;
	}
}
