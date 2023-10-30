package controllers;

import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import security.AppSecurity;

public class Acesso extends Controller {

    @Transactional
    public Result login() {
        try {
            return ok(views.html.acesso.login.render());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result register() {
        try {
            return ok(views.html.acesso.register.render());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result autenticar() {
        DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
        String email = dynamicForm.get("email");
        String password = dynamicForm.get("password");
        try {
            Usuario usuario = Usuario.buscarPor(email, password);
            session(AppSecurity.USUARIO_LOGADO, usuario.getId().toString());
            return ok();
        } catch (Exception e) {
            return unauthorized();
        }
    }

    @Transactional
    public Result logout() {
        session().clear();
        return redirect(routes.Acesso.login());
    }

    @Transactional
    public Result create() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String nome = dynamicForm.get("nome");
            String email = dynamicForm.get("email");
            String senha = dynamicForm.get("senha");

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            usuario.alterar();

            return ok();
        }catch (Exception e){
            return badRequest();
        }
    }
}
