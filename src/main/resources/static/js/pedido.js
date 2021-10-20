$(".status").click(function(){
	
	console.log(this.id)
	
	$.ajax({
		type: 'POST',
		url: '/pedido/alterarStatus',
		data: {hashPedido: this.id},
		async: true,
		success: function(){
			location.reload(true)
			
			}
		})
	
})


$(".cancelar").click(function(){
	
	console.log(this.id)
	
	Swal.fire({
		  title: 'Cancelar pedido?',
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
					url: '/pedido/cancelar',
					data: {hashPedido: this.id},
					async: true,
					success: function(){
						location.reload(true)
						
						}
					})
			}
		})
	
})


