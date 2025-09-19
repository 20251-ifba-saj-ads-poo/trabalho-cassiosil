package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CadSolicitacaoInvalidoException;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Solicitacao;

public class SolicitacaoService extends Service<Solicitacao> {

    public SolicitacaoService() {
        super(Solicitacao.class);
    }

    @Override
    public void validation(Solicitacao entity) throws ValidationException {
        if (entity.getEquipamento() == null || entity.getFuncionario() == null ||
            entity.getDataSolicitacao() == null || entity.getDataDevolucao() == null) {
            throw new CadSolicitacaoInvalidoException(
                "Não foi possível cadastrar a solicitacao, verifique se todos os campos estão preenchidos");
        }
    }
}
