package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class TarefaFinalizada extends TiposTarefas {

    @NotNull
    private String feedback;

    public TarefaFinalizada(Long id, String dataInicio, String dataFim, String feedback) {
        super(id, dataInicio, dataFim); // Chama o construtor da superclasse com os parâmetros correspondentes
        this.feedback = feedback;
    }

    public TarefaFinalizada() {

    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
