package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Equipamento;
import br.edu.ifba.saj.fwads.model.Funcionario;
import br.edu.ifba.saj.fwads.model.Solicitacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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


    @FXML
    private void salvarSolicitacao() {
        Solicitacao novoSolicitacao = new Solicitacao(slEquipamento.getSelectionModel().getSelectedItem(),
                    slFuncionario.getSelectionModel().getSelectedItem(), 
                    dtSolicitacao.getValue(), 
                    dtDevolucao.getValue());
                    
        new Alert(AlertType.INFORMATION,
        "Cadastrando Solicitacao: "+novoSolicitacao.toString()).showAndWait();
        Dados.listaSolicitacoes.add(novoSolicitacao);
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
                return Dados.listaEquipamentos
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
                return Dados.listaFuncionarios
                    .stream()
                    .filter(funcionario -> stringFuncionario.equals(funcionario.getNome() + ":" + funcionario.getMatricula()))
                    .findAny()
                    .orElse(null);                
            }
        });
        
        carregarlistaFuncionarios();
    }

    private void carregarlistaFuncionarios() {
        slFuncionario.setItems(Dados.listaFuncionarios);
    }

    private void carregarlistaEquipamentos() {
        slEquipamento.setItems(Dados.listaEquipamentos);
    }

    @FXML
    private void limparTela() {
        slEquipamento.setSelectionModel(null);
        slFuncionario.setSelectionModel(null);
        dtSolicitacao.setValue(null);
        dtDevolucao.setValue(null);
    }

}
