var Configuracoes = function () {

    var init = function (){
        $('#formConfig').validate({
            ignore: 'input[type=hidden]',
            rules: {
                appId: {
                    required: true,
                }
            },
            errorClass: "invalid-feedback-error text-danger",
            submitHandler: function (form) {

                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: '/configuracoes/salvar',
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        Swal.fire({
                            title: "Tudo certo :)",
                            text: "Configuração salva com sucesso",
                            icon: "success",
                            showCancelButton: false,
                            confirmButtonText: "OK"
                        }).then(function (result) {});
                    },
                    error: function (jqXHR, exception) {
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
