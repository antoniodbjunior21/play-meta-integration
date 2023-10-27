package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Audited
@Entity
@Table(name = "tipo_logradouro")
public class TipoLogradouro extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_tipo_logradouro", sequenceName = "seq_tipo_logradouro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_logradouro")
    private Long id;

    @NotEmpty
    @Column(length = 100)
    private String descricao;

    @NotEmpty
    @Column(length = 20)
    private String abreviatura;

    @Column(name = "prioridade_ordenacao")
    private Integer prioridadeOrdenacao;


    public TipoLogradouro() {
    }


    public TipoLogradouro(Long id) {
        this.id = id;
    }


    public static List<TipoLogradouro> buscarTodosTipos(){
        TypedQuery<TipoLogradouro> typedQuery = JPA.em().createQuery("SELECT t FROM TipoLogradouro t ORDER BY t.prioridadeOrdenacao,t.descricao ",TipoLogradouro.class);
        return typedQuery.getResultList();
    }

    public static TipoLogradouro buscarPor(String descricao){
        try{
            TypedQuery<TipoLogradouro> typedQuery = JPA.em().createQuery("SELECT t FROM TipoLogradouro t WHERE UPPER(t.descricao)=:descricao OR UPPER(t.abreviatura)=:descricao ",TipoLogradouro.class);
            typedQuery.setParameter("descricao",descricao.toUpperCase().trim());
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public static TipoLogradouro buscarPrimeiroTipo(){
        try{
            TypedQuery<TipoLogradouro> typedQuery = JPA.em().createQuery("SELECT t FROM TipoLogradouro t ORDER BY t.prioridadeOrdenacao ",TipoLogradouro.class);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public static List<TipoLogradouro> buscarTiposLogradouroPor(String descricao){
        TypedQuery<TipoLogradouro> typedQuery = JPA.em().createQuery("SELECT t FROM TipoLogradouro t WHERE UPPER(t.descricao)=:descricao OR UPPER(t.abreviatura)=:descricao ",TipoLogradouro.class);
        typedQuery.setParameter("descricao", descricao.toUpperCase().trim());
        return typedQuery.getResultList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Integer getPrioridadeOrdenacao() {
        return prioridadeOrdenacao;
    }

    public void setPrioridadeOrdenacao(Integer prioridadeOrdenacao) {
        this.prioridadeOrdenacao = prioridadeOrdenacao;
    }

}
