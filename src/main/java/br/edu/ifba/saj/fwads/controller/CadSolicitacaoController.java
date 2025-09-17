package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Solicitacao;
import br.edu.ifba.saj.fwads.model.Status;
import br.edu.ifba.saj.fwads.service.EquipamentoService;
import br.edu.ifba.saj.fwads.service.FuncionarioService;
import br.edu.ifba.saj.fwads.service.SolicitacaoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class CadSolicitacaoController {

    @FXML
    private ChoiceBox<Equipamento> slEquipamento;

    @FXML
    private ChoiceBox<Funcionario> slFuncionario;

    @FXML
    private DatePicker dtSolicitacao;
    @FXML

    private DatePicker dtDevolucao;

    private ListarEquipamentoController ListarEquipamentoController;
    private ListarFuncionarioController ListarFuncionarioController;
    private ListarSolicitacaoController ListarSolicitacaoController;

    private EquipamentoService equipamentoService = new EquipamentoService();
    private FuncionarioService funcionarioService = new FuncionarioService();
    private SolicitacaoService solicitacaoService = new SolicitacaoService();

    public void setListarSolicitacaoController(ListarSolicitacaoController ListarSolicitacaoController) {
        this.ListarSolicitacaoController = ListarSolicitacaoController;
    }

    @FXML
    private void salvarSolicitacao() {
        Solicitacao novoSolicitacao = new Solicitacao(slEquipamento.getSelectionModel().getSelectedItem(),
                    slFuncionario.getSelectionModel().getSelectedItem(), 
                    dtSolicitacao.getValue(), 
                    dtDevolucao.getValue());
        try {
            solicitacaoService.validaCad(novoSolicitacao);
            solicitacaoService.create(novoSolicitacao);
            equipamentoUpdate();
            new Alert(AlertType.INFORMATION,
            "Cadastrando Solicitacao: "+novoSolicitacao.toString()).showAndWait();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        }
        limparTela();
        
    }

    @FXML 
    private void initialize() {
        slEquipamento.setConverter(new StringConverter<Equipamento>() {
            @Override
            public String toString(Equipamento obj) {
                if (obj != null) {
                    return obj.getNome() + " : " + obj.getNumeroDeSerie();
                }
                return "";
            }

            @Override
            public Equipamento fromString(String stringEquipamento) {
                return equipamentoService.findAll()
                    .stream()
                    .filter(equipamento -> stringEquipamento.equals(equipamento.getNome() + " : " + equipamento.getNumeroDeSerie()))
                    .findAny()
                    .orElse(null);                
            }
        });
        
        carregarlistaEquipamentos();
    

        slFuncionario.setConverter(new StringConverter<Funcionario>() {
            @Override
            public String toString(Funcionario obj) {
                if (obj != null) {
                    return obj.getNome() + " : " + obj.getMatricula();
                }
                return "";
            }

            @Override
            public Funcionario fromString(String stringFuncionario) {
                return funcionarioService.findAll()
                    .stream()
                    .filter(funcionario -> stringFuncionario.equals(funcionario.getNome() + ":" + funcionario.getMatricula()))
                    .findAny()
                    .orElse(null);                
            }
        });
        
        carregarlistaFuncionarios();
    }

    private void carregarlistaFuncionarios() {
        slFuncionario.setItems(FXCollections.observableList(funcionarioService.findAll()));
    }

    private void carregarlistaEquipamentos() {
        slEquipamento.setItems(FXCollections.observableList(equipamentoService.findAll()));
    }
    
    public void equipamentoUpdate() {
        int selectedID = slEquipamento.getSelectionModel().getSelectedIndex();
        Equipamento equipamento = slEquipamento.getItems().get(selectedID);
        equipamento.alterarStatus(Status.EMUSO);
        equipamentoService.update(equipamento);
    }

    @FXML
    private void limparTela() {
        slEquipamento.setValue(null);
        slFuncionario.setValue(null);
        dtSolicitacao.setValue(null);
        dtDevolucao.setValue(null);
    }

}
