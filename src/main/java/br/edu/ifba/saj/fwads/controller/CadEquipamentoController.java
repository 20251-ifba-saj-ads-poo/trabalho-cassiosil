package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.CadEquipamentoInvalidoException;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.service.EquipamentoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class CadEquipamentoController {

    @FXML
    private TextField txnome;

    @FXML
    private TextField txnumeroDeSerie;

    @FXML
    private TextField txlocalizacao;

    private MasterController masterControllerFinal;
    private ListarEquipamentoController ListarEquipamentoController;

    private EquipamentoService equipamentoService = new EquipamentoService();
    
    public void setMasterController(MasterController masterController) {
        this.masterControllerFinal = masterControllerFinal;
    }

    public void setListarEquipamentoController(ListarEquipamentoController ListarEquipamentoController) {
        this.ListarEquipamentoController = ListarEquipamentoController;
    }

    @FXML
    private void salvarEquipamento() {
        Equipamento novoEquipamento = new Equipamento(txnome.getText(),
                    txnumeroDeSerie.getText(), 
                    txlocalizacao.getText());
        try {            
            equipamentoService.create(novoEquipamento);
            new Alert(AlertType.INFORMATION, 
            "Cadastrando Equipamento: "+novoEquipamento.getNome()).showAndWait();
        } catch (ValidationException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
        limparTela();
    }
    @FXML
    private void limparTela() {
        txnome.setText("");
        txnumeroDeSerie.setText("");
        txlocalizacao.setText("");
    }

}


