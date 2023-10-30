package models;

import org.hibernate.annotations.Type;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facebook_webhook")
public class FacebookWebhook extends BaseImobiliaria {

    public FacebookWebhook() {
    }

    public FacebookWebhook(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;

    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new Date();

    public static List<FacebookWebhook> buscar(){
        Query query = JPA.em().createQuery("SELECT fw FROM FacebookWebhook fw ");
        return query.getResultList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
