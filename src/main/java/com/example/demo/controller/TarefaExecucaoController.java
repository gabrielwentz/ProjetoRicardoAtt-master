package com.example.demo.controller;

import com.example.demo.model.TarefaExecucao;
import com.example.demo.service.TarefaExecucaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas-execucao")
public class TarefaExecucaoController {

    @Autowired
    TarefaExecucaoService tarefaExecucaoService;

    @GetMapping
    public List<TarefaExecucao> getAllTarefaExecucoes() {
        return tarefaExecucaoService.listar();
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody TarefaExecucao tarefaExecucao) {
        if (tarefaExecucao.getDescricaoTarefa() == null) {
            String mensagem = "O campo descricaoTarefa é obrigatório.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }

        TarefaExecucao tarefaCriada = tarefaExecucaoService.criar(tarefaExecucao);
        return ResponseEntity.ok(tarefaCriada);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TarefaExecucao tarefaExecucao) {
        if (tarefaExecucaoService.atualizar(id, tarefaExecucao) == null) {
            String mensagem = "O id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(tarefaExecucao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (tarefaExecucaoService.delete(id)) {
            String mensagem = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(mensagem);
        }
        String mensagem = "O id informado não existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/{id}")
    public Optional<TarefaExecucao> buscaPorID(@PathVariable Long id) {
        return tarefaExecucaoService.buscaPorID(id);
    }
}

