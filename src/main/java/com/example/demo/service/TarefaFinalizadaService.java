package com.example.demo.service;

import com.example.demo.model.TarefaFinalizada;
import com.example.demo.repository.TarefaFinalizadaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaFinalizadaService implements CrudService<TarefaFinalizada> {

    @Autowired
    TarefaFinalizadaRepository tarefaFinalizadaRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<TarefaFinalizada> listar (){
        return tarefaFinalizadaRepository.findAll();
    }

    @Override
    public TarefaFinalizada criar(TarefaFinalizada tarefaFinalizada){
        return tarefaFinalizadaRepository.save(tarefaFinalizada);
    }

    @Transactional
    public TarefaFinalizada atualizar(Long id, TarefaFinalizada tarefaFinalizada) {
        Optional<TarefaFinalizada> tarefaOptional = buscaPorID(id);
        if (tarefaOptional.isPresent()) {
            TarefaFinalizada tarefaExistente = tarefaOptional.get();


            if (tarefaFinalizada.getNomeTarefa() != null && !tarefaFinalizada.getNomeTarefa().isEmpty()) {
                tarefaExistente.setNomeTarefa(tarefaFinalizada.getNomeTarefa());
                tarefaExistente.setDataInicio(tarefaFinalizada.getDataInicio());
                tarefaExistente.setDataFim(tarefaFinalizada.getDataFim());
                tarefaExistente.setFeedback(tarefaFinalizada.getFeedback());

                try {
                    validator.validate(tarefaExistente);
                } catch (ConstraintViolationException e) {

                    throw e;
                }

                return tarefaFinalizadaRepository.save(tarefaExistente);
            } else {
                throw new IllegalArgumentException("O campo 'nomeTarefa' não pode ser nulo ou vazio.");
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean verificaID(Long id) {
        return tarefaFinalizadaRepository.existsById(id);
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
        return tarefaFinalizadaRepository.findById(id);
    }

    public long contarTarefasFinalizadas() {
        return tarefaFinalizadaRepository.count();
    }

}
