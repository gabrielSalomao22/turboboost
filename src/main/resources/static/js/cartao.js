$(".novo").click(function(){
	$(':input', "#exampleModal")
		.not(':button, :submit, :reset')
		.val('')
		.prop('checked', false)
		.prop('selected', false)
		
		$("#formCartao").attr('action', '/novoCartao');
});

$(".excluir").click(function(){
	let hashCartao = this.id;
	
	Swal.fire({
		  title: 'Excluir cartão?',
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
					url: '/deletarCartao',
					data: {hashCartao: hashCartao},
					async: true,
					success: function() {
						window.location.href = "/meusCartoes";
					}
					
				});
			}
		})
	
	
});

$(".editar").click(function(){
	$.ajax({
		type: 'GET',
		url: '/editarCartao',
		data: {hashCartao: this.id},
		async: true,
		success: function(data){
			
			$("#formCartao").attr('action', '/editarCartao');
			
			$("#hashCartao").val(data.hashCartao);
			$("#numeroCartao").val(data.numeroCartao);
			$("#nomeImpresso").val(data.nomeImpresso);
			$("#cvv").val(data.cvv);
			//$("#bandeira").val(data.bandeira);
			$("#bandeira option[value="+data.bandeira+"]").attr('selected', 'selected');
			
			$("#exampleModal").modal('show');
			
			
		}
	})
});