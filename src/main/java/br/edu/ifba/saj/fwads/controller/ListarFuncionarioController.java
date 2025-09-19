
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Permissao;
import br.edu.ifba.saj.fwads.service.FuncionarioService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TableColumn<Funcionario, Permissao> columnPermissao;
    @FXML
    private TableColumn<Funcionario, String> columnEmail;
    @FXML
    private TableColumn<Funcionario, String> columnLogin;

    private FuncionarioService funcionarioService = new FuncionarioService();

    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnPermissao.setCellValueFactory(new PropertyValueFactory<>("permissao"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        loadFuncionarioList();
        setColumnEdit();
        //new Alert(AlertType.INFORMATION, funcionarioService.findAll().toString()).showAndWait();
    }

    public void loadFuncionarioList() {
        tblFuncionario.setItems(FXCollections.observableList(new FuncionarioService().findAll()));
    }

    public void setColumnEdit() {
        tblFuncionario.setEditable(true);
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCPF.setCellFactory(TextFieldTableCell.forTableColumn());
        columnMatricula.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPermissao.setCellFactory(ComboBoxTableCell.forTableColumn((Permissao.values())));

        columnNome.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            String oldValue = event.getOldValue();
            funcionario.setNome(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                funcionario.setNome(oldValue);
                tblFuncionario.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnCPF.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            String oldValue = event.getOldValue();
            funcionario.setCpf(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                funcionario.setCpf(oldValue);
                tblFuncionario.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnMatricula.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            String oldValue = event.getOldValue();
            funcionario.setMatricula(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                funcionario.setMatricula(oldValue);
                tblFuncionario.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnEmail.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            String oldValue = event.getOldValue();
            funcionario.setEmail(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                funcionario.setEmail(oldValue);
                tblFuncionario.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnLogin.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            String oldValue = event.getOldValue();
            funcionario.setLogin(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                funcionario.setLogin(oldValue);
                tblFuncionario.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnPermissao.setOnEditCommit(event -> {
            Funcionario funcionario = event.getRowValue();
            funcionario.setPermissao(event.getNewValue());
            try {
                funcionarioService.update(funcionario);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        tblFuncionario.refresh();
    }

    @FXML
    public void removerFuncionario(MouseEvent event) {
        int selectedID = tblFuncionario.getSelectionModel().getSelectedIndex();
        Funcionario funcionario = tblFuncionario.getItems().get(selectedID);
        if (selectedID >= 0) {
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
