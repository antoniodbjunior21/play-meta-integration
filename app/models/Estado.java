package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
@Entity
@Table(name = "estado")
public class Estado extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
    private Long id;

    @NotEmpty
    @Column(length = 100)
    private String nome;

    @Column(name = "nome_fonetico", length = 200)
    private String nomeFonetico;

    @NotEmpty
    @Column(length = 20)
    private String sigla;


    public static Estado buscarPor(String sigla) {
        try {
            TypedQuery<Estado> typedQuery = JPA.em().createQuery("SELECT e FROM Estado e WHERE ( UPPER(e.sigla) = :sigla OR UPPER(e.nome)= :sigla ) ", Estado.class);
            typedQuery.setParameter("sigla", sigla.toUpperCase().trim());
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<Estado> buscarTodos(){
        Query query = JPA.em().createQuery("SELECT e FROM Estado e");
        return query.getResultList();
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
