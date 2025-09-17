
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Status;
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

    private Service<Equipamento> equipamentoService = new Service<>(Equipamento.class);

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
        tblEquipamento.setItems(FXCollections.observableList(new Service(Equipamento.class).findAll()));
    }
    
    public void setColumnEdit(){
        tblEquipamento.setEditable(true);
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumeroDeSerie.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLocalizacao.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStatus.setCellFactory(ComboBoxTableCell.forTableColumn((Status.values())));
        

        columnNome.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.setNome(event.getNewValue());
            equipamentoService.update(equipamento);
        });

        columnNumeroDeSerie.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.setNumeroDeSerie(event.getNewValue());
            equipamentoService.update(equipamento);
        });

        columnLocalizacao.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.setLocalizacao(event.getNewValue());
            equipamentoService.update(equipamento);
        });

        columnStatus.setOnEditCommit(event -> {
            Equipamento equipamento = event.getRowValue();
            equipamento.alterarStatus(event.getNewValue());
            equipamentoService.update(equipamento);
        });

        tblEquipamento.refresh();
    }

    @FXML
    public void removerEquipamento(MouseEvent event) {
        int selectedID = tblEquipamento.getSelectionModel().getSelectedIndex();
        Equipamento equipamento = tblEquipamento.getItems().get(selectedID);
        if(selectedID >= 0){
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

