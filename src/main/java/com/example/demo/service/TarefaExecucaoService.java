package com.example.demo.service;

import com.example.demo.model.TarefaExecucao;
import com.example.demo.repository.TarefaExecucaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaExecucaoService implements CrudService<TarefaExecucao> {

    @Autowired
    TarefaExecucaoRepository tarefaExecucaoRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<TarefaExecucao> listar() {
        return tarefaExecucaoRepository.findAll();
    }

    @Override
    public TarefaExecucao criar(TarefaExecucao tarefaExecucao) {
        return tarefaExecucaoRepository.save(tarefaExecucao);
    }

    @Transactional
    public TarefaExecucao atualizar(Long id, TarefaExecucao tarefaExecucao) {
        Optional<TarefaExecucao> tarefaOptional = buscaPorID(id);
        if (tarefaOptional.isPresent()) {
            TarefaExecucao tarefaExistente = tarefaOptional.get();

            if (tarefaExecucao.getNomeTarefa() != null && !tarefaExecucao.getNomeTarefa().isEmpty()) {
                tarefaExistente.setNomeTarefa(tarefaExecucao.getNomeTarefa());
                tarefaExistente.setDataInicio(tarefaExecucao.getDataInicio());
                tarefaExistente.setDataFim(tarefaExecucao.getDataFim());
                tarefaExistente.setDescricaoTarefa(tarefaExecucao.getDescricaoTarefa());

                try {
                    validator.validate(tarefaExistente);
                } catch (ConstraintViolationException e) {
                    throw e;
                }

                return tarefaExecucaoRepository.save(tarefaExistente);
            } else {
                throw new IllegalArgumentException("O campo 'nomeTarefa' não pode ser nulo ou vazio.");
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean verificaID(Long id) {
        return tarefaExecucaoRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if (verificaID(id)) {
            tarefaExecucaoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<TarefaExecucao> buscaPorID(Long id) {
        return tarefaExecucaoRepository.findById(id);
    }

    public long contarTarefasExecucao() {
        return tarefaExecucaoRepository.count();
    }


}
