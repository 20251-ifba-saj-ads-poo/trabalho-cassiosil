package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadFuncionarioController {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txCPF;

    @FXML
    private TextField txMatricula;

     private MasterController masterControllerFinal;
    private ListarEquipamentoController ListarEquipamentoController;

    private Service<Equipamento> serviceEquipamento = new Service<>(Equipamento.class);
    
    public void setMasterController(MasterController masterController) {
        this.masterControllerFinal = masterControllerFinal;
    }

    public void setlistarEquipamentoController(ListarEquipamentoController ListarEquipamentoController) {
        this.ListarEquipamentoController = ListarEquipamentoController;

    @FXML
    private void salvarFuncionario() {
        Funcionario novoFuncionario = new Funcionario(txNome.getText(),
                    txCPF.getText(), 
                    txMatricula.getText());
        new Alert(AlertType.INFORMATION, 
        "Cadastrando Funcionario: "+novoFuncionario.getNome()).showAndWait();
        Dados.listaFuncionarios.add(novoFuncionario);
        limparTela();
    }
    @FXML
    private void limparTela() {
        txNome.setText("");
        txCPF.setText("");
        txMatricula.setText("");
    }

}
