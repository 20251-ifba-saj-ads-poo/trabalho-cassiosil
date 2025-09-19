
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Status;
import br.edu.ifba.saj.fwads.service.EquipamentoService;
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

public class ListarEquipamentoController {
    @FXML
    private TableView<Equipamento> tblEquipamento;

    @FXML
    private TableColumn<Equipamento, String> columnNome;
    @FXML
    private TableColumn<Equipamento, String> columnNumeroDeSerie;
    @FXML
    private TableColumn<Equipamento, String> columnLocalizacao;
    @FXML
    private TableColumn<Equipamento, Status> columnStatus;

    private EquipamentoService equipamentoService = new EquipamentoService();

    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnNumeroDeSerie.setCellValueFactory(new PropertyValueFactory<>("numeroDeSerie"));
        columnLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadEquipamentoList();
        setColumnEdit();
    }

    public void loadEquipamentoList() {
        tblEquipamento.setItems(FXCollections.observableList(new EquipamentoService().findAll()));
    }

    public void setColumnEdit() {
        tblEquipamento.setEditable(true);
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumeroDeSerie.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLocalizacao.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStatus.setCellFactory(ComboBoxTableCell.forTableColumn((Status.values())));

        columnNome.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            String oldValue = event.getOldValue();
            equipamento.setNome(event.getNewValue());
            try {
                equipamentoService.update(equipamento);
            } catch (ValidationException e) {
                equipamento.setNome(oldValue);
                tblEquipamento.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        columnNumeroDeSerie.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.setNumeroDeSerie(event.getNewValue());
            try {
                equipamentoService.update(equipamento);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        columnLocalizacao.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.setLocalizacao(event.getNewValue());
            try {
                equipamentoService.update(equipamento);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        columnStatus.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.alterarStatus(event.getNewValue());
            try {
                equipamentoService.update(equipamento);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        tblEquipamento.refresh();
    }

    @FXML
    public void removerEquipamento(MouseEvent event) {
        int selectedID = tblEquipamento.getSelectionModel().getSelectedIndex();
        Equipamento equipamento = tblEquipamento.getItems().get(selectedID);
        if (selectedID >= 0) {
            tblEquipamento.getItems().remove(selectedID);
            equipamentoService.delete(equipamento);
        }
    }

    @FXML
    public void showNovoEquipamento() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadEquipamento.fxml"), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadEquipamentoController controller = (CadEquipamentoController) App.getController();
        controller.setListarEquipamentoController(this);
        stage.showAndWait();
    }

}
