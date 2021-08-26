$(".excluir").click(function(){
	let hashEndereco = this.id;
	
	Swal.fire({
		  title: 'Excluir endereço?',
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
					url: '/excluirEndereco',
					data: {hashEndereco: hashEndereco},
					async: true,
					success: function() {
						window.location.href = "/meusEnderecos";
					}
					
				});
			}
		})
	
	
});


