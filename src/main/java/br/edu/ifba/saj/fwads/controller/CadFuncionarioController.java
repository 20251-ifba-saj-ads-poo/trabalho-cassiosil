package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.service.FuncionarioService;
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

    private FuncionarioService funcionarioService = new FuncionarioService();
    
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
                    txSenha.getText(),
                    txLogin.getText(),
                    txEmail.getText());
        try {
            funcionarioService.validaCad(novoFuncionario);
            funcionarioService.create(novoFuncionario);
            new Alert(AlertType.INFORMATION, 
            "Cadastrando Funcionario: "+novoFuncionario.getNome()).showAndWait();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
        
        
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
