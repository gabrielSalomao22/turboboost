$(".deletar").click(function(){
	let hashProduto = this.id;
	
	Swal.fire({
		  title: 'Excluir produto?',
		  icon: 'info',
		  text: 'Não é possivel reverter esta ação',
		  showCloseButton: true,
		  showCancelButton: true,
		  focusConfirm: false,
		  confirmButtonText: 'Excluir',
		  confirmButtonColor: '#a81b0c',
		  cancelButtonText: 'Voltar',
		  cancelButtonColor: '#edb602'
		}).then((confirm) => {
			if(confirm.value){
				$.ajax({
					type: 'POST',
					url: '/produto/deletarProduto',
					data: {hashProduto: hashProduto},
					async: true,
					success: function() {
						window.location.href = "/produto/visualizar";
					}
					
				});
			}
		})
	
});


$(".inativar").click(function(){
	let hashProduto = this.id;
	
	$("#hashProdutoInativar").val(hashProduto);
	
	$("#modalInativar").modal('show');
});

$(".ativar").click(function(){
	let hashProduto = this.id;
	
	Swal.fire({
		  title: 'Ativar produto?',
		  icon: 'info',
		  text: 'Deseja ativar o novamente o produto?',
		  showCloseButton: true,
		  showCancelButton: true,
		  focusConfirm: false,
		  confirmButtonText: 'Ativar',
		  confirmButtonColor: '#2d8c12',
		  cancelButtonText: 'Voltar',
		  cancelButtonColor: '#edb602'
		}).then((confirm) => {
			if(confirm.value){
				$.ajax({
					type: 'POST',
					url: '/produto/ativarProduto',
					data: {hashProduto: hashProduto},
					async: true,
					success: function() {
						window.location.href = "/produto/visualizar";
					}
					
				});
			}
		})
	
});

$(".editar").click(function(){
	console.log(this.id);
	
	$.ajax({
		type: 'GET',
		url: '/produto/buscarEdicao',
		data: {hashProduto: this.id},
		async: true,
		success: function(data){
			
			$("#editarHashProduto").val(data.hashProduto);
			$("#editarPrecoCusto").val(data.precoCusto);
			$("#editarCategoria option[value="+data.categoria+"]").attr('selected', 'selected');
			$("#editarNome").val(data.nome);
			$("#editarSku").val(data.sku);
			$("#editarPreco").val(data.preco);
			$("#editarEstoque").val(data.estoque);
			
			
			$("#modalEditar").modal('show');
			
			
		}
	})
});

