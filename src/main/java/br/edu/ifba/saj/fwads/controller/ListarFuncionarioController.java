
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.Dados;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Permissao;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListarFuncionarioController {
    @FXML
    private TableView<Funcionario> tblFuncionario;

    @FXML
    private TableColumn<Funcionario, String> columnNome;
    @FXML
    private TableColumn<Funcionario, String> columnCPF;
    @FXML
    private TableColumn<Funcionario, String> columnMatricula;
    @FXML
    private TableColumn<Funcionario, Permissao> columnPermissao;

    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        columnPermissao.setCellValueFactory(new PropertyValueFactory<>("Permissao"));
        tblFuncionario.setItems(Dados.listaFuncionarios);
    }

    @FXML
    public void showNovoFuncionario() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadFuncionario.fxml"), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    @FXML
    public void removerFuncionario(MouseEvent event) {
        int selectedID = tblFuncionario.getSelectionModel().getSelectedIndex();
        if(selectedID >= 0){
            tblFuncionario.getItems().remove(selectedID);
        }
        
    }

}
