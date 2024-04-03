package com.example.demo.service;

import com.example.demo.model.TarefaCancelada;
import com.example.demo.repository.TarefaCanceladaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaCanceladaService implements CrudService<TarefaCancelada>{

    @Autowired
    private TarefaCanceladaRepository tarefaCanceladaRepository;

    @Override
    public List<TarefaCancelada> listar (){
        return tarefaCanceladaRepository.findAll();
    }

    @Override
    public TarefaCancelada criar(TarefaCancelada tarefaCancelada){
        return tarefaCanceladaRepository.save(tarefaCancelada);
    }

    @Override
    public TarefaCancelada atualizar(Long id, TarefaCancelada tarefaCancelada) {
        if(verificaID(id)) {
            tarefaCancelada.setId(id);
            return tarefaCanceladaRepository.save(tarefaCancelada);
        }
        return null;
    }

    @Override
    public boolean verificaID(Long id) {
        return this.tarefaCanceladaRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(verificaID(id)) {
            tarefaCanceladaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TarefaCancelada> buscaPorID(Long id) {
        return this.tarefaCanceladaRepository.findById(id);
    }

}
