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
