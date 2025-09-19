
package br.edu.ifba.saj.fwads.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.ValidationException;
import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Solicitacao;
import br.edu.ifba.saj.fwads.model.Status;
import br.edu.ifba.saj.fwads.model.StatusSolicitacao;
import br.edu.ifba.saj.fwads.service.EquipamentoService;
import br.edu.ifba.saj.fwads.service.FuncionarioService;
import br.edu.ifba.saj.fwads.service.SolicitacaoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.StringConverter;

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
    private ObservableList<Funcionario> funcionariosDisponiveis;
    private LocalDate localDateConverter;

    private SolicitacaoService solicitacaoService = new SolicitacaoService();

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

    public void loadSolicitacaoList() {
        tblSolicitacao.setItems(FXCollections.observableList(new SolicitacaoService().findAll()));

    }

    public void setColumnEdit() {
        tblSolicitacao.setEditable(true);
        List<Equipamento> todosEquipamentos = new EquipamentoService().findAll();
        List<Equipamento> filtrados = todosEquipamentos.stream()
                .filter(e -> e.getStatus() == Status.DISPONIVEL)
                .collect(Collectors.toList());
        equipamentosDisponiveis = FXCollections.observableList(filtrados);
        funcionariosDisponiveis = FXCollections.observableArrayList(new FuncionarioService().findAll());

        columnEquipamento.setCellFactory(ComboBoxTableCell.forTableColumn(equipamentosDisponiveis));
        columnFuncionario.setCellFactory(ComboBoxTableCell.forTableColumn(funcionariosDisponiveis));

        // columnDataSolicitacao.setCellFactory(DatePickerTableCell.forTableColumn(new
        // LocalDateStringConverter()));
        // columnDataDevolucao.setCellFactory(DatePickerTableCell.forTableColumn(new
        // LocalDateStringConverter()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringConverter<LocalDate> localDateConverter = new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? date.format(formatter) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    return LocalDate.parse(string, formatter);
                } catch (Exception e) {
                    return null;
                }
            }
        };
        columnDataSolicitacao.setCellFactory(TextFieldTableCell.forTableColumn(localDateConverter));
        columnDataDevolucao.setCellFactory(TextFieldTableCell.forTableColumn(localDateConverter));

        columnStatus.setCellFactory(ComboBoxTableCell.forTableColumn((StatusSolicitacao.values())));

        columnEquipamento.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setEquipamento(event.getNewValue());
            try {
                solicitacaoService.update(solicitacao);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnFuncionario.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setFuncionario(event.getNewValue());
            try {
                solicitacaoService.update(solicitacao);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        columnDataSolicitacao.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            LocalDate oldValue = event.getOldValue();
            solicitacao.setDataSolicitacao(event.getNewValue());
            try {
                solicitacaoService.update(solicitacao);
            } catch (ValidationException e) {
                solicitacao.setDataSolicitacao(oldValue);
                tblSolicitacao.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        columnDataDevolucao.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            LocalDate oldValue = event.getOldValue();
            solicitacao.setDataDevolucao(event.getNewValue());
            try {
                solicitacaoService.update(solicitacao);
            } catch (ValidationException e) {
                solicitacao.setDataSolicitacao(oldValue);
                tblSolicitacao.refresh();
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        columnStatus.setOnEditCommit(event -> {
            Solicitacao solicitacao = event.getRowValue();
            solicitacao.setStatus(event.getNewValue());
            try {
                solicitacaoService.update(solicitacao);
            } catch (ValidationException e) {
                new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
            }

        });

        tblSolicitacao.refresh();
    }

    @FXML
    public void removerSolicitacao(MouseEvent event) {
        int selectedID = tblSolicitacao.getSelectionModel().getSelectedIndex();
        Solicitacao solicitacao = tblSolicitacao.getItems().get(selectedID);
        if (selectedID >= 0) {
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
