
package br.edu.ifba.saj.fwads.controller;

import java.time.LocalDate;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Solicitacao;
import br.edu.ifba.saj.fwads.model.StatusSolicitacao;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListarSolicitacaoController {
    @FXML
    private TableView<Solicitacao> tblSolicitacao;

    @FXML
    private TableColumn<Solicitacao, Equipamento> columnEquipamento;
    @FXML
    private TableColumn<Solicitacao, Funcionario> columnFuncionario;
    @FXML
    private TableColumn<Solicitacao, LocalDate> columnDataSolicitacao;
    @FXML
    private TableColumn<Solicitacao, LocalDate> columnDataDevolucao;
    @FXML
    private TableColumn<Solicitacao, StatusSolicitacao> columnStatus;

    private ObservableList<Equipamento> equipamentosDisponiveis;
    private ObservableList<Funcionario> funcionarioDisponiveis;

    private Service<Solicitacao> solicitacaoService = new Service<>(Solicitacao.class);

    @FXML
    public void initialize() {
        columnEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
        columnFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        columnDataSolicitacao.setCellValueFactory(new PropertyValueFactory<>("dataSolicitacao"));
        columnDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadSolicitacaoList();        
        setColumnEdit();
    }

    public void loadSolicitacaoList(){
        tblSolicitacao.setItems(FXCollections.observableList(new Service(Solicitacao.class).findAll()));
        
    }

    public void setColumnEdit(){
        tblSolicitacao.setEditable(true);
        equipamentosDisponiveis = FXCollections.observableList(new Service(Equipamento.class).findAll());
        funcionarioDisponiveis = FXCollections.observableArrayList(new Service(Funcionario.class).findAll());
        columnEquipamento.setCellFactory(ComboBoxTableCell.forTableColumn(equipamentosDisponiveis));
        columnFuncionario.setCellFactory(ComboBoxTableCell.forTableColumn(funcionarioDisponiveis));

        //columnDataSolicitacao.setCellFactory(DatePickerTableCell.forTableColumn(new LocalDateStringConverter()));
        //columnDataDevolucao.setCellFactory(DatePickerTableCell.forTableColumn(new LocalDateStringConverter()));

        columnStatus.setCellFactory(ComboBoxTableCell.forTableColumn((StatusSolicitacao.values())));
        

        columnEquipamento.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setEquipamento(event.getNewValue());
            solicitacaoService.update(solicitacao);
        });

        columnFuncionario.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setFuncionario(event.getNewValue());
            solicitacaoService.update(solicitacao);
        });

        columnDataSolicitacao.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setDataSolicitacao(event.getNewValue());
            solicitacaoService.update(solicitacao);
        });

        columnDataDevolucao.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setDataDevolucao(event.getNewValue());
            solicitacaoService.update(solicitacao);
        });

        columnStatus.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setStatus(event.getNewValue());
            solicitacaoService.update(solicitacao);
        });

        tblSolicitacao.refresh();
    }
    
    @FXML
    public void removerSolicitacao(MouseEvent event) {
        int selectedID = tblSolicitacao.getSelectionModel().getSelectedIndex();
        Solicitacao solicitacao = tblSolicitacao.getItems().get(selectedID);
        if(selectedID >= 0){
            tblSolicitacao.getItems().remove(selectedID);
            solicitacaoService.delete(solicitacao);
        }
        
    }

    @FXML
    public void showNovaSolicitacao() {

        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadSolicitacao.fxml"), 800, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        CadSolicitacaoController controller = (CadSolicitacaoController) App.getController();
        controller.setListarSolicitacaoController(this);

        stage.showAndWait();

    }

}
