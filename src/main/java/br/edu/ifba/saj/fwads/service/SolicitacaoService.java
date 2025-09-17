package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CadSolicitacaoInvalidoException;
import br.edu.ifba.saj.fwads.model.Solicitacao;

public class SolicitacaoService extends Service<Solicitacao> {

    public SolicitacaoService() {
        super(Solicitacao.class);
    }

    public void validaCad(Solicitacao novoSolicitacao) throws CadSolicitacaoInvalidoException {
        if (novoSolicitacao.getEquipamento() == null || novoSolicitacao.getFuncionario() == null ||
            novoSolicitacao.getDataSolicitacao() == null || novoSolicitacao.getDataDevolucao() == null) {
            throw new CadSolicitacaoInvalidoException(
                "Não foi possível cadastrar a solicitacao, verifique se todos os campos estão preenchidos");
        }
    }
}
