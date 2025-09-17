
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Permissao;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private TableColumn<Funcionario, String> columnEmail;
    @FXML
    private TableColumn<Funcionario, String> columnLogin;
    @FXML
    private TableColumn<Funcionario, Permissao> columnPermissao;

    private Service<Funcionario> funcionarioService = new Service<>(Funcionario.class);

    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("Login"));
        columnPermissao.setCellValueFactory(new PropertyValueFactory<>("Permissao"));
        loadFuncionarioList();
        setColumnEdit();
    }

    public void loadFuncionarioList() {
        tblFuncionario.setItems(FXCollections.observableList(new Service(Funcionario.class).findAll()));
    }

    public void setColumnEdit(){
        tblFuncionario.setEditable(true);
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCPF.setCellFactory(TextFieldTableCell.forTableColumn());
        columnMatricula.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPermissao.setCellFactory(ComboBoxTableCell.forTableColumn((Permissao.values())));
        

        columnNome.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setNome(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        columnCPF.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setCpf(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        columnMatricula.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setMatricula(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        columnEmail.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setEmail(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        columnLogin.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setLogin(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        columnPermissao.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setPermissao(event.getNewValue());
            funcionarioService.update(funcionario);
        });

        tblFuncionario.refresh();
    }

    @FXML
    public void removerFuncionario(MouseEvent event) {
        int selectedID = tblFuncionario.getSelectionModel().getSelectedIndex();
        Funcionario funcionario = tblFuncionario.getItems().get(selectedID);
        if(selectedID >= 0){
            tblFuncionario.getItems().remove(selectedID);
            funcionarioService.delete(funcionario);
        }
    }

    @FXML
    public void showNovoFuncionario() {
        
        Stage stage = new Stage();            
        Scene scene = new Scene(App.loadFXML("controller/CadFuncionario.fxml"), 800, 600);            
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        CadFuncionarioController controller = (CadFuncionarioController) App.getController();
        controller.setListarFuncionarioController(this);
        stage.showAndWait();
    }
}
