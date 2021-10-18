$(".troca").click(function(){
	
	$("#produtos").empty();
	
	$.ajax({
		type: 'GET',
		url: '/pedido/buscarInfos',
		data: {hashPedido: this.id},
		async: true,
		success: function(data){
				console.log(data);
				
				
				$("#codigoPedido").text("Código do pedido: " + data.codigo);
				$("#dataPedido").text("Data do pedido: " + data.dataFormatada);
				$("#valorPedido").text("Valor do pedido: R$" + data.precoTotal);
				$("#statusPedido").text("Status do pedido: " + data.status);
				$("#hashPedido").val(data.hashPedido);
				
				for(var i = 0; i < data.produtosDTO.length; i++){
					$("#produtos").append(
					`<div class="row">
                        <div class="form-group form-check ml-2">
                            <input type="checkbox" name="` + data.produtosDTO[i].hashProduto + `" id="" class="form-check-input selecionado">
                            <label for="" class="form-check-label">` + data.produtosDTO[i].nome + ` - <span>R$` + data.produtosDTO[i].preco + `</span></label>
                            <input type="hidden" name="hashProduto" value="` + data.produtosDTO[i].hashProduto + `" id="` + data.produtosDTO[i].hashProduto + `" disabled>
                        </div>
                    </div>`
					)
					
				}
				
			
			}
	})
	
})


$('body').on('click', '.selecionado', function(){
	if($(this).is(":checked")){
		let id = "#" + $(this).attr('name');
		
		$(id).prop("disabled", false);
		
	}else{
		let id = "#" + $(this).attr('name');
		
		$(id).prop("disabled", true);
	}
	
	
});

$(".status").click(function(){
	
	console.log(this.id)
	
	$.ajax({
		type: 'POST',
		url: '/troca/alterarStatus',
		data: {hashPedido: this.id},
		async: true,
		success: function(){
			location.reload(true)
			
			}
		})
	
})


