package controllers;

import beans.FiltroPessoaBean;
import beans.PessoaBean;
import beans.search.SearchResult;
import models.Pessoa;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class Pessoas extends Controller {

    private static Form<Pessoa> formCadastro = Form.form(Pessoa.class);
    private static Form<FiltroPessoaBean> formFiltro = Form.form(FiltroPessoaBean.class);

    @Transactional
    public Result abrirPaginaInicial() {
        try {
            return ok(views.html.pessoa.inicio.render());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result filtrar(){
        try {
            Form<FiltroPessoaBean> form = formFiltro.bindFromRequest();
            FiltroPessoaBean filtro = form.get();
            SearchResult searchResult = Pessoa.buscarPor(filtro);
            return ok(Json.toJson(searchResult));
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {
            Form<Pessoa> form = formCadastro.bindFromRequest();
            Pessoa pessoa = form.get();
            pessoa = (Pessoa) pessoa.alterar();
            return ok();
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

}
