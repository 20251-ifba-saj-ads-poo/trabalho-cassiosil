package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CadEquipamentoInvalidoException;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Equipamento;

public class EquipamentoService extends Service<Equipamento> {

    public EquipamentoService() {
        super(Equipamento.class);
    }

    @Override
    protected void validation(Equipamento entity) throws ValidationException {
        if (entity.getNome().isEmpty() || entity.getNumeroDeSerie().isEmpty() ||
                entity.getLocalizacao().isEmpty()) {
            throw new CadEquipamentoInvalidoException(
                    "Não foi possível cadastrar o equipamento, verifique se todos os campos estão preenchidos");
        }
    }

}
