package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public abstract class Tarefa {

    @NotNull
    public String nomeTarefa;

    public Tarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public Tarefa() {
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
