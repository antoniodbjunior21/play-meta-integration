package controllers;

import models.Usuario;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;

@Security.Authenticated(AppSecurity.class)
public class Application extends Controller {
    public Result inicio() {
        return ok(views.html.inicio.render());
    }
    public Result dashboard() {
        return ok(views.html.dashboard.render());
    }

    @Transactional
    public static Usuario obterUsuarioLogado(){
        return AppSecurity.obterUsuarioLogado();
    }
}
