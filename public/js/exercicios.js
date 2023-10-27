var Exercicios = function () {
    const URL_SALVAR_PESSOA = '/pessoa/salvar'

    var init = function (){
        iniciarExercicio1();
    }

    var iniciarExercicio1 = function (){
        $('#formExercicio1').validate({
            ignore: 'input[type=hidden]',
            rules:{
                nome: {
                    required: true
                }
            },
            messages: {
                nome: {
                    required: 'Informe o nome'
                }
            },
            errorClass: "invalid-feedback-error text-danger",
            submitHandler: function (form) {

                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR_PESSOA,
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        Swal.fire({
                            title: "Tudo certo :)",
                            text: "Pessoa salva com sucesso!",
                            icon: "success"
                        });
                    },
                    error: function (jqXHR, exception) {
                        Swal.fire({
                            title: 'Oops!',
                            text: 'Algo deu errado, tente novamente mais tarde',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        })
                    }
                });
            }
        });
    }
    return{
        init: function (){
            init();
        }
    }
}()