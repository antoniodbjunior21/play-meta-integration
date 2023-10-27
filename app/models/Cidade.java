package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown=true)
@Audited
@Entity
@Table(name = "cidade")
public class Cidade extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade")
    private Long id;

    @NotEmpty
    @Column(length = 200)
    private String nome;

    @Column(name = "nome_fonetico",length = 200)
    private String nomeFonetico;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    private Boolean capital = Boolean.FALSE;

    @Column(name = "prioridade_ordenacao")
    private Integer prioridadeOrdenacao;



    public static  Cidade buscarCidadePor(String nome, Estado estado) {
        try {
            TypedQuery<Cidade> typedQuery = JPA.em().createQuery("SELECT c FROM Cidade c " +
                    "WHERE UPPER(c.nome)=:nome AND c.estado=:estado", Cidade.class);
            typedQuery.setParameter("nome", nome.toUpperCase().trim());
            typedQuery.setParameter("estado", estado);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFonetico() {
        return nomeFonetico;
    }

    public void setNomeFonetico(String nomeFonetico) {
        this.nomeFonetico = nomeFonetico;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public Integer getPrioridadeOrdenacao() {
        return prioridadeOrdenacao;
    }

    public void setPrioridadeOrdenacao(Integer prioridadeOrdenacao) {
        this.prioridadeOrdenacao = prioridadeOrdenacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
