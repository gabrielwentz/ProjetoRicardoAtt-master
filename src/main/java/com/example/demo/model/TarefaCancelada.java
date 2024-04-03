package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class TarefaCancelada extends TiposTarefas {

    @NotNull(message = "O campo motivoCancelamento não pode ser nulo")
    private String motivoCancelamento;

    public TarefaCancelada(Long id, String dataInicio, String dataFim, String motivoCancelamento) {
        super(id, dataInicio, dataFim);
        this.motivoCancelamento = motivoCancelamento;
    }

    public TarefaCancelada() {
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
