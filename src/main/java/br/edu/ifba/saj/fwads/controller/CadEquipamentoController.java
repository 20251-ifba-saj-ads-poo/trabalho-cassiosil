package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class CadEquipamentoController {

    @FXML
    private TextField txnome;

    @FXML
    private TextField txnumeroDeSerie;

    @FXML
    private TextField txlocalizacao;

    private MasterController masterControllerFinal;
    private ListarEquipamentoController ListarEquipamentoController;

    private Service<Equipamento> serviceEquipamento = new Service<>(Equipamento.class);
    
    public void setMasterController(MasterController masterController) {
        this.masterControllerFinal = masterControllerFinal;
    }

    public void setlistarEquipamentoController(ListarEquipamentoController ListarEquipamentoController) {
        this.ListarEquipamentoController = ListarEquipamentoController;

    @FXML
    private void salvarEquipamento() {
        Equipamento novoEquipamento = new Equipamento(txnome.getText(),
                    txnumeroDeSerie.getText(), 
                    txlocalizacao.getText());
        serviceEquipamento.create(novoEquipamento);
        new Alert(AlertType.INFORMATION, 
        "Cadastrando Equipamento: "+novoEquipamento.getNome()).showAndWait();
        txnome.setText("");
        txnumeroDeSerie.setText("");
        txlocalizacao.setText("");;
    }
    @FXML
    private void limparTela() {
        txnome.setText("");
        txnumeroDeSerie.setText("");
        txlocalizacao.setText("");
    }

}
