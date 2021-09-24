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

$('input[name=quantidadeItem]').change(function(){
	console.log(this.value);
	console.log($('input[name=' + this.id +']').val())
	
	let precoNovo = parseFloat($("#precoTotal").val()) + parseFloat($('input[name=' + this.id +']').val());
	console.log(precoNovo);
	$("#precoTotal").val(precoNovo);
	$("#valorText").text("Total: R$" + precoNovo);
	
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

$("#formEndereco").on("submit", function(e){
	var dataString = $(this).serialize();
	
	console.log("ta no ajax")
	
	$.ajax({
		type: 'POST',
		url: "/novoEnderecoPedido",
		data: dataString,
		success: function(data){
			$("#enderecos").append(
				`<div class="form-check" id="enderecos">
            		<input class="form-check-input" type="radio" name="hashEndereco" id="` + data.hashEndereco + `" value="` + data.hashEndereco + `" required>
            		<label class="form-check-label">` + data.nomeEndereco +`</label>
            	</div>`
			)
		}
	});
	
	e.preventDefault();
	$('#modalEndereco').modal('hide');
})

$("#formCartao").on("submit", function(e){
	var dataString = $(this).serialize();
	
	console.log("ajax cartao")
	
	$.ajax({
		type: 'POST',
		url: "/novoCartaoPedido",
		data: dataString,
		success: function(data){
			$("#cartoes").append(
				`<div class="form-check" id="enderecos">
            		<input class="form-check-input" type="radio" name="hashCartao" id="` + data.hashCartao + `" value="` + data.hashCartao + `" required>
            		<label class="form-check-label">` + data.bandeira +`</label>
            	</div>`
			)
		}
	});
	
	e.preventDefault();
	$('#modalCartao').modal('hide');
})



