package br.edu.ifba.saj.fwads.model;

import java.time.LocalDate;

public class Solicitacao extends AbstractEntity {
    private Equipamento equipamento;
    private Funcionario funcionario;
    private LocalDate dataSolicitacao;
    private LocalDate dataDevolucao;
    private StatusSolicitacao status;
    public Solicitacao(Equipamento equipamento, Funcionario funcionario, LocalDate dataSolicitacao, LocalDate dataDevolucao) {
        this.equipamento = equipamento;
        this.funcionario = funcionario;
        this.dataSolicitacao = dataSolicitacao;
        this.dataDevolucao = dataDevolucao;
        this.status = StatusSolicitacao.EMANDAMENTO;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public StatusSolicitacao getStatus() {
        return status;
    }
    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
 
    @Override
    public String toString() {
        return "Solicitacao [equipamento = " + equipamento.getNumeroDeSerie() + ", funcionario = " + funcionario.getMatricula() +
         ", dataSolicitacao = " + dataSolicitacao + ", dataDevolucao = " + dataDevolucao + "]";
    }

    
}
