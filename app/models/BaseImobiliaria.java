package models;

import exceptions.BaseException;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseImobiliaria extends BaseEntidade{

    private Long codigo;

    @Column(name = "codigo_migracao")
    private String codigoMigracao;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();

    public Long buscarProximoCodigo(){
        String classeName = this.getClass().getSimpleName();
        Query query = JPA.em().createQuery(" SELECT COALESCE(MAX(e.codigo),0) FROM " + classeName + " e ");
        return (Long) query.getSingleResult();
    }

    @PrePersist
    @PreUpdate
    public void onCreate() throws BaseException {
        if(this.codigo == null){
            codigo = this.buscarProximoCodigo() + 1;
        }
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoMigracao() {
        return codigoMigracao;
    }

    public void setCodigoMigracao(String codigoMigracao) {
        this.codigoMigracao = codigoMigracao;
    }


    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
