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

$(".editar").click(function(){
	$.ajax({
		type: 'GET',
		url: '/editarEndereco',
		data: {hashEndereco: this.id},
		async: true,
		success: function(data){
			
			$("#formEndereco").attr('action', '/editarEndereco');
			$("#hashEndereco").val(data.hashEndereco);
			$("#nomeEndereco").val(data.nomeEndereco);
			$("#cep").val(data.cep);
			$("#logradouro").val(data.logradouro);
			$("#numero").val(data.numero);
			
			if(data.tipoLogradouro == 'privado'){
				$("#privado").prop("checked", true);
				
			}else{
				$("#publico").prop('checked', true);
			}
			
			if(data.tipoResidencia == 'apartamento'){
				$("#apartamento").prop('checked', true);
				
			}else{
				$("#casa").prop('checked', true);
			}
			
			$("#bairro").val(data.bairro)
			$("#cidade").val(data.cidade);
			$("#estado").val(data.estado);
			$("#pais").val(data.pais);
			$("#observacao").val(data.observacao);
			
			$("#exampleModal").modal('show');
			
			
		}
	})
});

$(".novo").click(function(){
	$(':input', "#exampleModal")
		.not(':button, :submit, :reset')
		.val('')
		.prop('checked', false)
		.prop('selected', false)
		
		$("#formEndereco").attr('action', '/novoEndereco');
});
