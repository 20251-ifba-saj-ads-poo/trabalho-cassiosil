package br.edu.ifba.saj.fwads.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Funcionario extends Usuario {
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String matricula;
    @Enumerated(EnumType.ORDINAL)
    private Permissao permissao;

    public Funcionario(){
        
    }

    public Funcionario(String nome, String cpf, String matricula, String senha, String login, String email) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.permissao = permissao.USUARIO;
        this.senha = senha;
        this.login = login;
        this.email = email;

        
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Permissao getPermissao() {
        return permissao;
    }
    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @Override
    public String toString() {
        return super.toString()+"Funcionario [nome = " + nome + ", CPF = " + cpf +
         ", matricula = " + matricula + 
         ", permissao = " + permissao + "]";
    }
}
