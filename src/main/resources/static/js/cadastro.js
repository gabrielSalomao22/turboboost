$("#cep").blur(function(){
    var cep = $(this).val().replace(/\D/g, '');

    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#logradouro").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#estado").val(dados.uf);
                                $("#pais").val('Brasil');
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });   
})

function next(idBotao){
    $('#' + idBotao).click();
}

function prev(idBotao){
    $('#' + idBotao).click();
}

$('#cep').mask('00000-000');
$("#cpf").mask('000.000.000-00');
$('#telefone').mask('(00) 00000-0000');