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
	
	$.ajax({
		type: 'POST',
		url: '/pedido/cancelar',
		data: {hashPedido: this.id},
		async: true,
		success: function(){
			location.reload(true)
			
			}
		})
	
})


