package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class TarefaExecucao extends TiposTarefas{

    @NotNull
    private String descricaoTarefa;

    public TarefaExecucao(Long id, String dataInicio, String dataFim, String descricaoTarefa) {
        super(id, dataInicio, dataFim);
        this.descricaoTarefa = descricaoTarefa;
    }

    public TarefaExecucao() {
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }
}
