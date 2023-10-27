package controllers;

import models.Usuario;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;

import static security.AppSecurity.USUARIO_LOGADO;

@Security.Authenticated(AppSecurity.class)
public class Application extends Controller {

    @Transactional
    public static Usuario obterUsuarioLogado() throws Throwable {
        String strId = Http.Context.current().session().get(USUARIO_LOGADO);
        return new Usuario();
        //return JPA.withTransaction(()-> Usuario.buscarPorId(Long.valueOf(strId)));
    }
    public Result inicio() {
        return ok(views.html.inicio.render());
    }
    public Result dashboard() {
        return ok(views.html.dashboard.render());
    }
}
