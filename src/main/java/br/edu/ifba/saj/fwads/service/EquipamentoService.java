package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CadEquipamentoInvalidoException;
import br.edu.ifba.saj.fwads.model.Equipamento;

public class EquipamentoService extends Service<Equipamento> {

    public EquipamentoService() {
        super(Equipamento.class);
    }

    public void validaCad(Equipamento novoEquipamento) throws CadEquipamentoInvalidoException {
        if (novoEquipamento.getNome().isEmpty() || novoEquipamento.getNumeroDeSerie().isEmpty() ||
            novoEquipamento.getLocalizacao().isEmpty()) {
            throw new CadEquipamentoInvalidoException(
                "Não foi possível cadastrar o equipamento, verifique se todos os campos estão preenchidos");
        }
    }
}
