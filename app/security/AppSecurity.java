package security;

import models.Usuario;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Http.Context.Implicit.session;

public class AppSecurity extends Security.Authenticator {

    public static final String USUARIO_LOGADO = "USUARIO_LOGADO";

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get(USUARIO_LOGADO);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect("/login");
    }

    public static Usuario obterUsuarioLogado(){
        return (Usuario) Usuario.buscarPorId(Usuario.class, Long.valueOf(session().get(USUARIO_LOGADO)));
    }
}
