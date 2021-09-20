$(".adicionar").click(function(){

	let hashProduto = this.id;
	
	$('<input>').attr({
		type: 'hidden',
		id: 'itemCarrinho',
		name: 'itemCarrinho',
		value: hashProduto
	}).appendTo('form');
	
	
	$(this).prop('disabled', true);
})

$(".aplicar").click(function(){

	let cupom = $("#cupom").val();
	
	$.ajax({
		type: 'GET',
		url: '/cupom/aplicarCupom',
		data: {codigo: cupom},
		async: true,
		statusCode: {
			200: function(data){
				
				let valorTotal = $("#precoTotal").val();
				
				if(data.porcentagem != null){
					
					if($("#cupomPromocional").length){
						alert("Cupom promocional já utilizado");
						
					}else{
						let porcentagem = data.porcentagem / 100;
						
						let valorParcial = valorTotal * porcentagem;
						let novoValor = valorTotal - valorParcial
						
						$("#precoTotal").val(novoValor);
						$("#valorText").text("Total: R$" + novoValor);
						
						$('<input>').attr({
							type: 'hidden',
							id: 'cupomPromocional',
							name: 'cupomPromocional',
							value: data.hashCupom
						}).appendTo('form');
						
					}
					
				}else{
					
						let novoValor = valorTotal - data.valor;
						$("#precoTotal").val(novoValor);
						$("#valorText").text("Total: R$" + novoValor);
						
						$('<input>').attr({
							type: 'hidden',
							id: 'cupomCliente',
							name: 'cupomCliente',
							value: data.hashCupom
						}).appendTo('form');
						
					
					
					
					
				}
				
				
			},
			
			404: function(){
				alert("Cupom inválido!")
			}
		
		}
	});
		
});


