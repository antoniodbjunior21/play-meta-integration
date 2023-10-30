var Auth = function () {

    var init = function (){
        initLoginForm()
    }

    var initLoginForm = function (){
        $('#formLogin').validate({
            ignore: 'input[type=hidden]',
            rules: {
                email: {
                    required: true,
                },
                password: {
                    required: true
                }
            },
            messages: {
                email: {
                    required: 'Informe o email',
                },
                password: {
                    required: 'Informe a senha',
                }
            },
            errorClass: "invalid-feedback-error text-danger",
            submitHandler: function (form) {

                var formData = $(form).serializeArray();

                console.log(formData)

                $.ajax({
                    method: 'POST',
                    url: '/autenticar',
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        console.log('success');
                        window.location.replace("/");
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
