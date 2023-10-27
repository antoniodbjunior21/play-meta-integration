var IntegracaoFacebook = function () {

    var userId;
    var userAccessToken;
    var linkPaginasTokens;

    var init = function (){

        setTimeout(() => {
            console.log('verificando se esta logado...')
            checkLoginState();
        }, 1500)

        $('#btn-logout').click(()=>{
            logout();
        })

        $('#btnIniciarWebhook').click(()=>{
            var pageId = $('#idPaginaInput').val()
            var pageToken = $('#tokenPaginaInput').val()
            window.open("/facebook/cadastrar-webhook?pageId="+pageId+"&pageToken="+pageToken);
        })

        $('#btnAppsInstalados').click(()=>{
            var pageId = $('#idPaginaInput').val()
            var pageToken = $('#tokenPaginaInput').val()
            window.open("/facebook/apps-instalados?pageId="+pageId+"&pageToken="+pageToken);
        })
    }

    var checkLoginState =  function () {
        FB.getLoginStatus(function (response) {
            if (response.status === 'connected') {
                console.log(response);
                userAccessToken = response.authResponse.accessToken;
                userId = response.authResponse.userID;
                obterDadosUsuarioLogado();
                mostrarDadosUsuarioLogado(true);
            }else{
                userAccessToken = null;
                userId = null;
                mostrarDadosUsuarioLogado(false);
            }
        });
    }

    var mostrarDadosUsuarioLogado = function (mostrar){
        if (mostrar){
            $('#dados-usuario h5').html('Usuário logado')
            $('#btn-logout').show();
            $('#btn-busca-paginas').show();
        }else{
            $('#btn-logout').hide();
            $('#dados-usuario h5').html('Nenhum usuario logado')
            $('#dados-usuario code').html('')
            $('#btn-busca-paginas').hide();
            $("#dados-paginas a").attr("href", " ")
        }
    }
    var obterDadosUsuarioLogado = function (){
        $.ajax({
            method: 'GET',
            url: "/facebook/user?token=" + userAccessToken,
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (response) {
                console.log(response)
                $('#dados-usuario code').html(JSON.stringify(response));
                linkPaginasTokens = '/facebook/pages-tokens?accessToken='+userAccessToken+"&userId="+userId;
                $("#dados-paginas a").attr("href", linkPaginasTokens)
            },
            error: function (jqXHR, exception) {
                console.log(exception);
            }
        });
    }

    var logout = function () {
        FB.logout();
        checkLoginState();
    }

    return{
        init: function (){
            init();
        },
        checkLoginState: function (){
            checkLoginState();
        }
    }
}()
