var FacebookAplicativo = function () {

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
                    url: '/facebook/configuracoes/salvar',
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        alert("Salvo com sucesso")
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
