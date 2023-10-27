package beans;

import beans.search.FiltroBaseBean;
import models.Pessoa;

public class FiltroPessoaBean extends FiltroBaseBean<Pessoa> {
    public String nome;

    public FiltroPessoaBean() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
