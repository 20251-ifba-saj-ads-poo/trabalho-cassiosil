package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Equipamento extends AbstractEntity {
    @Column
    private String nome;
    @Column
    private String numeroDeSerie;
    @Column
    private String localizacao;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Equipamento(){
        
    }

    public Equipamento(String nome, String numeroDeSerie, String localizacao) {
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
        this.localizacao = localizacao;
        this.status = status.DISPONIVEL;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }
    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Status getStatus() {
        return status;
    }
    public void alterarStatus(Status novoStatus) {
        this.status = novoStatus;
    }

    @Override
    public String toString() {
        return "Equipamento [nome = " + nome + ", numero de serie = " +
         numeroDeSerie + ", localizacao = " + localizacao + ", status = " + status + "]";
    }

    
}
