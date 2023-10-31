package controllers;

import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;

@Security.Authenticated(AppSecurity.class)
public class Configuracoes extends Controller {

    @Transactional
    public Result abrirPainelConfiguracoes() {
        try {
            Usuario usuario = AppSecurity.obterUsuarioLogado();
            return ok(views.html.configuracoes.render(usuario.getFacebookAppId()));
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String appId = dynamicForm.get("facebookAppId");

            Usuario usuario = AppSecurity.obterUsuarioLogado();
            usuario.setFacebookAppId(appId);
            usuario.alterar();

            return ok();
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

}
