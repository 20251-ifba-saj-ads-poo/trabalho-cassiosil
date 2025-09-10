package br.edu.ifba.saj.fwads.model;

public class Funcionario extends AbstractEntity {
    private String nome;
    private String cpf;
    private String matricula;
    private String senha;
    private Permissao permissao;

    public Funcionario(String nome, String cpf, String matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.permissao = permissao.USUARIO;
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

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return permissao;
    }
    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @Override
    public String toString() {
        return "Funcionario [nome = " + nome + ", CPF = " + cpf +
         ", matricula = " + matricula + ", permissao = " + permissao + "]";
    }
}
