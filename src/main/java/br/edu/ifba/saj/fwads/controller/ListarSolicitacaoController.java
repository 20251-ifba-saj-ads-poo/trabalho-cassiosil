
package br.edu.ifba.saj.fwads.controller;

import java.time.LocalDate;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Solicitacao;
import br.edu.ifba.saj.fwads.model.StatusSolicitacao;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void initialize() {
        columnEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
        columnFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        columnDataSolicitacao.setCellValueFactory(new PropertyValueFactory<>("dataSolicitacao"));
        columnDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadLivroList();        
    }

    public void loadLivroList(){
        tblSolicitacao.setItems(FXCollections.observableList(new Service(Solicitacao.class).findAll()));
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
