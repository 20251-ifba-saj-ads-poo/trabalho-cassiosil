package br.edu.ifba.saj.fwads.service;

import java.util.Map;
import java.util.NoSuchElementException;

import br.edu.ifba.saj.fwads.exception.CadFuncionarioInvalidoException;
import br.edu.ifba.saj.fwads.exception.LoginInvalidoException;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Funcionario;

public class FuncionarioService extends Service<Funcionario> {

    public FuncionarioService() {
        super(Funcionario.class);
    }

    public Funcionario validaLogin(String login, String senha) throws LoginInvalidoException {
        try {
            return findByMap(Map.of("login", login, "senha", senha)).getFirst();
        } catch (NoSuchElementException e) {
            throw new LoginInvalidoException(
                "Não foi possível localizar o usuário " + login + ", ou a senha esta errada");
        }
    }

    
    @Override
    public void validation(Funcionario entity) throws ValidationException {
        if (entity.getNome().isEmpty() || entity.getCpf().isEmpty() || entity.getMatricula().isEmpty() || 
            entity.getEmail().isEmpty() || entity.getLogin().isEmpty() || entity.getSenha().isEmpty()) {
            throw new CadFuncionarioInvalidoException(
                "Não foi possível cadastrar o funcionario, verifique se todos os campos estão preenchidos");
        }
    }
}
