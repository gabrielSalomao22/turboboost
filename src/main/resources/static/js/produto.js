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
	
})
