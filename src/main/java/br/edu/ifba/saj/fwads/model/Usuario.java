package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class Usuario extends AbstractEntity {

    @Column
    @Email
    protected String email;
    @Column
    @NotBlank
    @Size(min = 5)
    protected String login;
    @Column
    @NotBlank
    @Size(min = 5)
    protected String senha;
    
   
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Usuario [senha=" + senha + ", login=" + login + ", email=" + email + "]";
    }

    

}
