package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Exercicios extends Controller {

    @Transactional
    public Result abrirPaginaInicial() {
        try {
            return ok(views.html.exercicios.render());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

}
