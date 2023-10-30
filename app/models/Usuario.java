package models;

import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;

@Entity
public class Usuario extends BaseImobiliaria {

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    private String senha;

    @Column(name = "app_id")
    private String appId;

    public static Usuario buscarPorId(Long id) {
        Query query = JPA.em().createQuery("SELECT u FROM Usuario u WHERE u.id = :id ");
        query.setParameter("id", id);
        return (Usuario) query.getSingleResult();
    }

    public static Usuario buscarPor(String email, String senha) {
        Query query = JPA.em().createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha");
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        return (Usuario) query.getSingleResult();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
