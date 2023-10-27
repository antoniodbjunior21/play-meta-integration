package models;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Audited
@Entity
@Table(name = "item_contato")
public class ItemContato extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_item_contato", sequenceName = "seq_item_contato", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_contato")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_contato_id")
    private TipoContato tipoContato;

    @NotEmpty
    private String valor;

    @Column(length = 100)
    private String observacao;

    @Enumerated(EnumType.STRING)
    private FinalidadeContatoEnum finalidade;

    @Column(name = "codigo_migracao")
    private String codigoMigracao;

    public static List<ItemContato> buscarPor(Pessoa pessoa){
        Query query = JPA.em().createQuery("SELECT ct FROM Pessoa p JOIN p.contatos ct WHERE p = :pessoa ");
        query.setParameter("pessoa", pessoa);
        return query.getResultList();
    }
    public ItemContato() {
    }

    public ItemContato(TipoContato tipoContato, String valor) {
        this.tipoContato = tipoContato;
        this.valor = valor;
    }

    public ItemContato(TipoContato tipoContato, String valor, String observacao) {
        this.tipoContato = tipoContato;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Boolean ehEmail(){
        if (this.tipoContato == null){
            return false;
        }
        return Boolean.TRUE.equals(this.tipoContato.getEmail());
    }
    public Boolean ehCelular(){
        if (this.tipoContato == null){
            return false;
        }
        return Boolean.TRUE.equals(this.tipoContato.getCelular());
    }

    public Boolean ehFixo(){
        if (this.tipoContato == null){
            return false;
        }
        return Boolean.TRUE.equals(this.tipoContato.getFixo());
    }

    public Boolean ehWhatsapp(){
        if (this.tipoContato == null){
            return false;
        }
        return Boolean.TRUE.equals(this.tipoContato.getWhatsapp());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public FinalidadeContatoEnum getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(FinalidadeContatoEnum finalidade) {
        this.finalidade = finalidade;
    }

    public String getCodigoMigracao() {
        return codigoMigracao;
    }

    public void setCodigoMigracao(String codigoMigracao) {
        this.codigoMigracao = codigoMigracao;
    }
}