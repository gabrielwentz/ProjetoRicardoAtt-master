package com.example.demo.service;

import com.example.demo.model.TarefaCancelada;
import com.example.demo.model.TarefaExecucao;
import com.example.demo.repository.TarefaCanceladaRepository;
import com.example.demo.repository.TarefaExecucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaExecucaoService implements CrudService<TarefaExecucao>{

    @Autowired
    TarefaExecucaoRepository tarefaExecucaoRepository;

    @Override
    public List<TarefaExecucao> listar (){
        return tarefaExecucaoRepository.findAll();
    }

    @Override
    public TarefaExecucao criar(TarefaExecucao tarefaExecucao){
        return tarefaExecucaoRepository.save(tarefaExecucao);
    }

    @Override
    public TarefaExecucao atualizar(Long id, TarefaExecucao tarefaExecucao) {
        if(verificaID(id)) {
            tarefaExecucao.setId(id);
            return tarefaExecucaoRepository.save(tarefaExecucao);
        }
        return null;
    }

    @Override
    public boolean verificaID(Long id) {
        return this.tarefaExecucaoRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(verificaID(id)) {
            tarefaExecucaoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TarefaExecucao> buscaPorID(Long id) {
        return this.tarefaExecucaoRepository.findById(id);
    }

}


