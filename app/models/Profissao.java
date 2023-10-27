package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
@Entity
@Table(name = "profissao")
public class Profissao extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_profissao", sequenceName = "seq_profissao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profissao")
    private Long id;

    @NotEmpty
    @Column(length = 200)
    private String nome;

    @Column(name = "nome_fonetico")
    private String nomeFonetico;

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
}
