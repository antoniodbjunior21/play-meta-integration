package models;

import exceptions.BaseException;
import play.db.jpa.JPA;

import javax.persistence.*;

@Entity
@Table(name = "configuracao_geral")
public class ConfiguracaoGeral extends BaseEntidade {

    public ConfiguracaoGeral() {
    }

    public ConfiguracaoGeral(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "seq_configuracao_geral", sequenceName = "seq_configuracao_geral", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_configuracao_geral")
    private Long id;

    @Column(name = "facebook_app_id")
    public String facebookAppId;

    public static String obterFacebookAppId() throws BaseException {
        ConfiguracaoGeral configuracaoGeral = buscar();
        return configuracaoGeral.getFacebookAppId();
    }

    public static ConfiguracaoGeral buscar() throws BaseException {
        ConfiguracaoGeral configuracao = null;

        try {
            Query query = JPA.em().createQuery("SELECT cg FROM ConfiguracaoGeral cg ");
            configuracao = (ConfiguracaoGeral) query.getSingleResult();
        }catch (NoResultException ignored){}

        if (configuracao == null){
            configuracao = new ConfiguracaoGeral();
            configuracao = (ConfiguracaoGeral) configuracao.alterar();
        }

        return configuracao;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebookAppId() {
        return facebookAppId;
    }

    public void setFacebookAppId(String facebookAppId) {
        this.facebookAppId = facebookAppId;
    }
}
