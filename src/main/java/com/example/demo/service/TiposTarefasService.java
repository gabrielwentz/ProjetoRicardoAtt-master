package com.example.demo.service;

import com.example.demo.model.TiposTarefas;
import com.example.demo.repository.TiposTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiposTarefasService implements CrudService<TiposTarefas> {

    @Autowired
    private TiposTarefaRepository tiposTarefaRepository;

    @Override
    public List<TiposTarefas> listar() {
        return tiposTarefaRepository.findAll();
    }

    public TiposTarefas criar(TiposTarefas tiposTarefas) {
        tiposTarefaRepository.save(tiposTarefas);
        return tiposTarefas;

    }

    public TiposTarefas atualizar(Long id, TiposTarefas tiposTarefas) {
        if (verificaID(id)) {
            tiposTarefas.setId(id);
            return tiposTarefas;
        }
        return null;

    }

    @Override
    public boolean verificaID(Long id) {
        return this.tiposTarefaRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if (verificaID(id)) {
            tiposTarefaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TiposTarefas> buscaPorID(Long id) {
        return tiposTarefaRepository.findById(id);
    }

}
