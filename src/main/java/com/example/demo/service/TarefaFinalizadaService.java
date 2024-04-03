package com.example.demo.service;

import com.example.demo.model.TarefaFinalizada;
import com.example.demo.repository.TarefaFinalizadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaFinalizadaService implements CrudService<TarefaFinalizada>{

    @Autowired
    TarefaFinalizadaRepository tarefaFinalizadaRepository;

    @Override
    public List<TarefaFinalizada> listar (){
        return tarefaFinalizadaRepository.findAll();
    }

    @Override
    public TarefaFinalizada criar(TarefaFinalizada tarefaFinalizada){
        return tarefaFinalizadaRepository.save(tarefaFinalizada);
    }

    @Override
    public TarefaFinalizada atualizar(Long id, TarefaFinalizada tarefaFinalizada) {
        if(verificaID(id)) {
            tarefaFinalizada.setId(id);
            return tarefaFinalizadaRepository.save(tarefaFinalizada);
        }
        return null;
    }

    @Override
    public boolean verificaID(Long id) {
        return this.tarefaFinalizadaRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(verificaID(id)) {
            tarefaFinalizadaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TarefaFinalizada> buscaPorID(Long id) {
        return this.tarefaFinalizadaRepository.findById(id);
    }
}


