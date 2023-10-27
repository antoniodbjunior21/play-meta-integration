package models;

import exceptions.BaseException;
import play.db.jpa.JPA;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BaseEntidade {

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    public Date dataCadastro = new Date();

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_cadastro")
    public Date horaCadastro = new Date();

    public void desanexar(){
        JPA.em().detach(this);
    }

    public void persistir(){
        JPA.em().flush();
    }


    public BaseEntidade salvar() throws BaseException {
        JPA.em().persist(this);
        return this;
    }

    public BaseEntidade alterar() throws BaseException {
        return JPA.em().merge(this);
    }


    public void excluir() throws BaseException {

        BaseEntidade baseEntidade = this;

        if(!JPA.em().contains(this)){
            baseEntidade = JPA.em().merge(this);
        }
        JPA.em().remove(baseEntidade);
    }

    public static BaseEntidade buscarPorId(Class classe,Long id){
        return (BaseEntidade) JPA.em().find(classe, id);
    }

    public static List<BaseEntidade> buscarPorIds(Class classe, List<Long> ids){
        TypedQuery<BaseEntidade> query = JPA.em().createQuery("select x from " + classe.getSimpleName() + " x WHERE x.id IN(:ids) ", BaseEntidade.class);
        query.setParameter("ids",ids);
        return query.getResultList();
    }

    public static List<BaseEntidade> buscarTodos(Class classe) {
        TypedQuery<BaseEntidade> query = JPA.em().createQuery("select x from " + classe.getSimpleName() + " x", classe);
        return query.getResultList();
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(Date horaCadastro) {
        this.horaCadastro = horaCadastro;
    }
}
