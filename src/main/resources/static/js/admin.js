$(".deletar").click(function(){
	
	Swal.fire({
		  title: 'Deletar cliente?',
		  icon: 'info',
		  text: 'Não é possivel reverter esta ação',
		  showCloseButton: true,
		  showCancelButton: true,
		  focusConfirm: false,
		  confirmButtonText: 'Deletar',
		  confirmButtonColor: '#a81b0c',
		  cancelButtonText: 'Voltar',
		  cancelButtonColor: '#edb602'
		}).then((confirm) => {
			if(confirm.value){
				$.ajax({
					type: 'POST',
					url: '/admin/deletarCliente',
					data: {hashCliente: this.id},
					async: true,
					success: function(){
						location.reload(true)
						
						}
					})
			}
		})
})