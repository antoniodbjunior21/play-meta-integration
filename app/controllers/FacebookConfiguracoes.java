package controllers;

import models.ConfiguracaoGeral;
import models.FacebookConfiguracao;
import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;


@Security.Authenticated(AppSecurity.class)
public class FacebookConfiguracoes extends Controller {

    @Transactional
    public Result abrirPainelIntegracao() {
        try {
            Usuario usuario = AppSecurity.obterUsuarioLogado();
            String facebookAppId = ConfiguracaoGeral.obterFacebookAppId();
            FacebookConfiguracao configuracao = FacebookConfiguracao.buscarExistenteOuCriarPor(usuario);
            return ok(views.html.facebook.integracao.render(configuracao, facebookAppId));
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result abrirPainelAplicativo() {
        try {
            Usuario usuario = AppSecurity.obterUsuarioLogado();
            ConfiguracaoGeral configuracaoGeral = ConfiguracaoGeral.buscar();
            return ok(views.html.facebook.aplicativo.render(configuracaoGeral));
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    @Security.Authenticated(AppSecurity.class)
    public Result salvarAppId() {
        try {
            ConfiguracaoGeral configuracaoGeral = ConfiguracaoGeral.buscar();

            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String appId = dynamicForm.get("facebookAppId");

            configuracaoGeral.setFacebookAppId(appId);

            return ok();
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }
}
