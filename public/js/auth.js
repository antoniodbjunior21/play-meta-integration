var Auth = function () {

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
                        SnackBar.show("Bem vindo!", 3000);
                        window.location.replace("/");
                    },
                    error: function (jqXHR, exception) {
                        SnackBar.show("Nenhum usuário encontrado", 3000);
                    }
                });
            }
        });
    }

    var initRegisterForm = function (){
        $('#registerForm').validate({
            ignore: 'input[type=hidden]',
            rules: {
                nome: {
                    required: true,
                },
                email: {
                    required: true,
                },
                senha: {
                    required: true
                }
            },
            errorClass: "invalid-feedback-error text-danger",
            submitHandler: function (form) {

                var formData = $(form).serializeArray();

                console.log(formData)

                $.ajax({
                    method: 'GET',
                    url: '/create',
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        Swal.fire({
                            title: "Tudo certo :)",
                            text: "Usuário criado com sucesso",
                            icon: "success",
                            showCancelButton: false,
                            confirmButtonText: "OK"
                        }).then(function (result) {
                            window.location.replace("/");
                        });
                    },
                    error: function (jqXHR, exception) {

                    }
                });
            }
        });
    }
    return{
        initLoginForm: function (){
            initLoginForm();
        },
        initRegisterForm: function (){
            initRegisterForm();
        }
    }
}()
