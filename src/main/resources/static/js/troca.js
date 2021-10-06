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
				
				for(var i = 0; i < data.produtosDTO.length; i++){
					$("#produtos").append(
					`<div class="row">
                        <div class="form-group form-check ml-2">
                            <input type="checkbox" name="` + data.produtosDTO[i].hashProduto + `" id="" class="form-check-input">
                            <label for="" class="form-check-label">` + data.produtosDTO[i].nome + ` - <span>R$` + data.produtosDTO[i].preco + `</span></label>
                        </div>
                    </div>`
					)
					
				}
				
				console.log(data.produtosDTO[1].hashProduto)
			
			}
	})
	
	
})