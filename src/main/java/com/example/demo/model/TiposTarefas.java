package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TiposTarefas extends Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^\\d{8}$", message = "A data de início deve estar no formato YYYYMMDD")
    private  String dataInicio;

    @NotNull
    @Pattern(regexp = "^\\d{8}$", message = "A data de fim deve estar no formato YYYYMMDD")
    private String dataFim;

    public TiposTarefas(Long id, String dataInicio, String dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }


    public TiposTarefas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public TiposTarefas(Long id, String dataInicio, String dataFim, String descricao) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }


    }

