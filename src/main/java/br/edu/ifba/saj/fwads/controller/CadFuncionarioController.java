package br.edu.ifba.saj.fwads.controller;

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

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txLogin;

    @FXML
    private TextField txSenha;

     private MasterController masterController;
    private ListarFuncionarioController ListarFuncionarioController;

    private Service<Funcionario> serviceFuncionario = new Service<>(Funcionario.class);
    
    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public void setListarFuncionarioController(ListarFuncionarioController ListarFuncionarioController) {
        this.ListarFuncionarioController = ListarFuncionarioController;
    }

    @FXML
    private void salvarFuncionario() {
        Funcionario novoFuncionario = new Funcionario(txNome.getText(),
                    txCPF.getText(), 
                    txMatricula.getText(),
                    txEmail.getText(),
                    txLogin.getText(),
                    txSenha.getText());
        new Alert(AlertType.INFORMATION, 
        "Cadastrando Funcionario: "+novoFuncionario.getNome()).showAndWait();
        serviceFuncionario.create(novoFuncionario);
        limparTela();
    }
    @FXML
    private void limparTela() {
        txNome.setText("");
        txCPF.setText("");
        txMatricula.setText("");
        txEmail.setText("");
        txLogin.setText("");
        txSenha.setText("");
    }


}
