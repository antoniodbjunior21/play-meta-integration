package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Audited
@Entity
@Table(name = "bairro")
public class Bairro extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_bairro", sequenceName = "seq_bairro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bairro")
    private Long id;

    @NotEmpty
    @Column(length = 200)
    private String nome;

    @Column(name = "nome_fonetico")
    private String nomeFonetico;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Bairro() {
    }


    public static  List<Bairro> todos(){
        TypedQuery<Bairro> typedQuery = JPA.em().createQuery(" SELECT b FROM Bairro b order by b.nome ", Bairro.class);
        return typedQuery.getResultList();
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
