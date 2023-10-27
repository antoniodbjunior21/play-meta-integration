package models;

import beans.FiltroPessoaBean;
import beans.PessoaBean;
import beans.search.SearchResult;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;
import utils.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Audited
@Entity
public class Pessoa extends BaseImobiliaria {

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;

    @NotEmpty
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fonetico")
    private String nomeFonetico;

    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum tipo = TipoPessoaEnum.FISICA;

    @Column(name = "cpf_ou_cnpj")
    private String cpfOuCnpj;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Embedded
    private Endereco endereco = new Endereco();

    private String senha;
    private Boolean ativo = true;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String observacao;

    private String rg;

    @Column(name = "orgao_emissor_rg")
    private String orgaoEmissorRg;

    private String naturalidade;
    private String nacionalidade;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivilEnum estadoCivil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissao_id")
    private Profissao profissao;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    private String cnh;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_validade_cnh")
    private Date dataValidadeCnh;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_cnh")
    private Date dataEmissaoCnh;

    @Column(name = "local_trabalho")
    private String localTrabalho;

    @Column(name = "numero_carteira_trabalho")
    private String numeroCarteiraTrabalho;

    @Column(name = "serie_carteira_trabalho")
    private String serieCarteiraTrabalho;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_carteira_trabalho")
    private Date dataEmissaoCarteiraTrabalho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conjugue_id")
    private Pessoa conjugue;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<ItemContato> contatos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PESSOA_JURIDICA_SOCIO", joinColumns = {@JoinColumn(name = "PESSOA_JURIDICA_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "SOCIO_ID", referencedColumnName = "ID")})
    private List<Pessoa> socios = new ArrayList<Pessoa>();


    public static List<String> coletarNomesDe(List<Pessoa> pessoas){
        if (pessoas == null || pessoas.size() == 0){
            return new ArrayList<>();
        }
        List<String> nomes = pessoas.stream().map(pessoa -> pessoa.nome).collect(toList());
        return nomes;
    }

    public static String coletarNomesFmtDe(List<Pessoa> pessoas){
        List<String> nomes = coletarNomesDe(pessoas);
        String nomesFmt = String.join(", ", nomes);
        return nomesFmt;
    }

    public ItemContato celular(){
        for (ItemContato contato: this.contatos){
            if(contato.getTipoContato() != null && contato.getTipoContato().getCelular() != null && contato.getTipoContato().getCelular()){
                return contato;
            }
        }
        return null;
    }

    public ItemContato email(){
        for(ItemContato contato: this.contatos){
            if(contato.getTipoContato() != null && contato.getTipoContato().getEmail() != null && contato.getTipoContato().getEmail()){
                return contato;
            }
        }
        return null;
    }

    public ItemContato whatsapp(){
        for (ItemContato contato: this.contatos){
            if(contato.getTipoContato() != null && contato.getTipoContato().getWhatsapp() != null && contato.getTipoContato().getWhatsapp()){
                return contato;
            }
        }
        return null;
    }

    public static ItemContato buscarContatoCelularPor(Pessoa pessoa){
        List<ItemContato> todosPorPessoa = ItemContato.buscarPor(pessoa);
        if (todosPorPessoa.size()>0){
            for(ItemContato contato: todosPorPessoa){
                if(contato.getTipoContato() != null && contato.getTipoContato().getCelular() != null && contato.getTipoContato().getCelular()){
                    ItemContato itemContato = contato;
                    return itemContato;
                }
            }
        }

        return null;
    }

    public static ItemContato buscarContatoWhatsAppPor(Pessoa pessoa){
        List<ItemContato> todosPorPessoa = ItemContato.buscarPor(pessoa);
        if (todosPorPessoa.size()>0){
            for(ItemContato contato: todosPorPessoa){
                if(contato.getTipoContato().getWhatsapp() != null && contato.getTipoContato().getWhatsapp()){
                    ItemContato itemContato = contato;
                    return itemContato;
                }
            }
        }

        return null;
    }

    public static List<Pessoa> todas(){
        TypedQuery<Pessoa> typedQuery = JPA.em().createQuery("SELECT p FROM Pessoa p ORDER BY p.nome ", Pessoa.class);
        return typedQuery.getResultList();
    }

    public static SearchResult<PessoaBean> buscarPor(FiltroPessoaBean filtro){
        String select = "SELECT new beans.PessoaBean(p.id, p.nome, p.ativo) FROM Pessoa p ";

        StringBuffer where = new StringBuffer(" WHERE 1=1 ");

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            where.append(" AND UPPER(p.nome) LIKE :nome ");
        }

        Query query = JPA.em().createQuery(select + where + " ORDER BY p.id ");
        query.setFirstResult(filtro.start.intValue());
        query.setMaxResults(filtro.length.intValue());

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome", "%"+filtro.nome.trim().toUpperCase()+"%");
        }

        List<PessoaBean> pessoas = query.getResultList();

        select = "SELECT COUNT (p.id) FROM Pessoa p ";

        query = JPA.em().createQuery(select + where);

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome", "%"+filtro.nome.trim().toUpperCase()+"%");
        }

        Long totalRegistros = (Long) query.getSingleResult();

        return new SearchResult<>(filtro.draw, pessoas, totalRegistros, filtro);
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFonetico() {
        return nomeFonetico;
    }

    public void setNomeFonetico(String nomeFonetico) {
        this.nomeFonetico = nomeFonetico;
    }

    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissorRg() {
        return orgaoEmissorRg;
    }

    public void setOrgaoEmissorRg(String orgaoEmissorRg) {
        this.orgaoEmissorRg = orgaoEmissorRg;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public EstadoCivilEnum getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDataValidadeCnh() {
        return dataValidadeCnh;
    }

    public void setDataValidadeCnh(Date dataValidadeCnh) {
        this.dataValidadeCnh = dataValidadeCnh;
    }

    public Date getDataEmissaoCnh() {
        return dataEmissaoCnh;
    }

    public void setDataEmissaoCnh(Date dataEmissaoCnh) {
        this.dataEmissaoCnh = dataEmissaoCnh;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getNumeroCarteiraTrabalho() {
        return numeroCarteiraTrabalho;
    }

    public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
        this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
    }

    public String getSerieCarteiraTrabalho() {
        return serieCarteiraTrabalho;
    }

    public void setSerieCarteiraTrabalho(String serieCarteiraTrabalho) {
        this.serieCarteiraTrabalho = serieCarteiraTrabalho;
    }

    public Date getDataEmissaoCarteiraTrabalho() {
        return dataEmissaoCarteiraTrabalho;
    }

    public void setDataEmissaoCarteiraTrabalho(Date dataEmissaoCarteiraTrabalho) {
        this.dataEmissaoCarteiraTrabalho = dataEmissaoCarteiraTrabalho;
    }

    public Pessoa getConjugue() {
        return conjugue;
    }

    public void setConjugue(Pessoa conjugue) {
        this.conjugue = conjugue;
    }

    public List<ItemContato> getContatos() {
        return contatos;
    }

    public void setContatos(List<ItemContato> contatos) {
        this.contatos = contatos;
    }

    public List<Pessoa> getSocios() {
        return socios;
    }

    public void setSocios(List<Pessoa> socios) {
        this.socios = socios;
    }
 }
