package com.example.demo.service;

import com.example.demo.model.TarefaCancelada;
import com.example.demo.repository.TarefaCanceladaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaCanceladaService implements CrudService<TarefaCancelada>{

    @Autowired
    private TarefaCanceladaRepository tarefaCanceladaRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<TarefaCancelada> listar (){
        return tarefaCanceladaRepository.findAll();
    }

    @Override
    public TarefaCancelada criar(TarefaCancelada tarefaCancelada){
        return tarefaCanceladaRepository.save(tarefaCancelada);
    }

    @Transactional
    public TarefaCancelada atualizar(Long id, TarefaCancelada tarefaCancelada) {
        Optional<TarefaCancelada> tarefaOptional = buscaPorID(id);
        if (tarefaOptional.isPresent()) {
            TarefaCancelada tarefaExistente = tarefaOptional.get();

            if (tarefaCancelada.getNomeTarefa() != null && !tarefaCancelada.getNomeTarefa().isEmpty()) {
                tarefaExistente.setNomeTarefa(tarefaCancelada.getNomeTarefa());
                tarefaExistente.setDataInicio(tarefaCancelada.getDataInicio());
                tarefaExistente.setDataFim(tarefaCancelada.getDataFim());
                tarefaExistente.setMotivoCancelamento(tarefaCancelada.getMotivoCancelamento());

                try {
                    validator.validate(tarefaExistente);
                } catch (ConstraintViolationException e) {
                    throw e;
                }

                return tarefaCanceladaRepository.save(tarefaExistente);
            } else {
                throw new IllegalArgumentException("O campo 'nomeTarefa' não pode ser nulo ou vazio.");
            }
        } else {
            return null;
        }
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

    public long contarTarefasCanceladas() {
        return tarefaCanceladaRepository.count();
    }

}
