package models;

import exceptions.BaseException;
import play.db.jpa.JPA;

import javax.persistence.*;

@Entity
@Table(name = "facebook_configuracao")
public class FacebookConfiguracao extends BaseEntidade {

    public FacebookConfiguracao() {
    }

    public FacebookConfiguracao(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "seq_webhook", sequenceName = "seq_webhook", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_webhook")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    public static FacebookConfiguracao buscarExistenteOuCriarPor(Usuario usuario) throws BaseException {
        FacebookConfiguracao configuracao = buscar(usuario);
        if (configuracao == null){
            configuracao = new FacebookConfiguracao();
            configuracao.setUsuario(usuario);
            configuracao = (FacebookConfiguracao) configuracao.alterar();
        }
        return configuracao;
    }

    public static FacebookConfiguracao buscar(Usuario usuario){
        try {
            Query query = JPA.em().createQuery("SELECT fc FROM FacebookConfiguracao fc WHERE fc.usuario = :usuario ");
            query.setParameter("usuario", usuario);
            return (FacebookConfiguracao) query.getSingleResult();
        }catch (NoResultException n){
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
