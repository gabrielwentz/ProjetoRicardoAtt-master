package com.example.demo.service;

import com.example.demo.model.TiposTarefas;

import java.util.List;

public interface CrudService <T extends TiposTarefas>{
    List<T> listar();
    T criar (T entity);

    T atualizar (Long id, T updatedEntity);

    boolean verificaID(Long id);

    boolean delete(Long id);

}
