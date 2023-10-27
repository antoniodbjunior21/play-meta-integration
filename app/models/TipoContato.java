package models;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

@Audited
@Entity
@Table(name = "tipo_contato")
public class TipoContato extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_tipo_contato", sequenceName = "seq_tipo_contato", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_contato")
    private Long id;

    @NotEmpty
    @Column(length = 100)
    private String descricao;

    @Column(length = 40)
    private String mascara;

    @Column(name = "exibir_grid")
    private Boolean exibirGrid = Boolean.FALSE;

    private Boolean email = Boolean.FALSE;

    private Boolean celular = Boolean.FALSE;

    private Boolean whatsapp = Boolean.FALSE;

    private Boolean fixo = Boolean.FALSE;


    public static List<TipoContato> buscarTodos(){
        TypedQuery<TipoContato> typedQuery = JPA.em().createQuery("SELECT t FROM TipoContato t ORDER BY t.id ",TipoContato.class);
        return typedQuery.getResultList();
    }

    public static TipoContato buscarTipoCelular(){
        try{
            TypedQuery<TipoContato> typedQuery = JPA.em().createQuery("SELECT t FROM TipoContato t WHERE t.celular=true",TipoContato.class);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public static TipoContato buscarTipoCelularWathsapp(){
        try{
            TypedQuery<TipoContato> typedQuery = JPA.em().createQuery("SELECT t FROM TipoContato t WHERE t.whatsapp=true",TipoContato.class);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public static TipoContato buscarTipoFixo(){
        try{
            TypedQuery<TipoContato> typedQuery = JPA.em().createQuery("SELECT t FROM TipoContato t WHERE t.fixo=true",TipoContato.class);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public static TipoContato buscarTipoEmail(){
        try{
            TypedQuery<TipoContato> typedQuery = JPA.em().createQuery("SELECT t FROM TipoContato t WHERE t.email=true",TipoContato.class);
            typedQuery.setMaxResults(1);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
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

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public Boolean getExibirGrid() {
        return exibirGrid;
    }

    public void setExibirGrid(Boolean exibirGrid) {
        this.exibirGrid = exibirGrid;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Boolean getCelular() {
        return celular;
    }

    public void setCelular(Boolean celular) {
        this.celular = celular;
    }

    public Boolean getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }
}