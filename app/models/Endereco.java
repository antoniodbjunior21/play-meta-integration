package models;

import javax.persistence.*;

public class Endereco {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_logradouro_id")
    private TipoLogradouro tipoLogradouro;

    @Column(length = 255)
    private String logradouro;

    @Column(name = "logradouro_fonetico", length = 255)
    private String logradouroFonetico;

    @Column(length = 255)
    private String numero;

    @Column(length = 255)
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Column(length = 15)
    private String cep;

    @Column(length = 30)
    private String latitude;

    @Column(length = 30)
    private String longitude;

    @Transient
    private String logradouroCorreio;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouroFonetico() {
        return logradouroFonetico;
    }

    public void setLogradouroFonetico(String logradouroFonetico) {
        this.logradouroFonetico = logradouroFonetico;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLogradouroCorreio() {
        return logradouroCorreio;
    }

    public void setLogradouroCorreio(String logradouroCorreio) {
        this.logradouroCorreio = logradouroCorreio;
    }
}