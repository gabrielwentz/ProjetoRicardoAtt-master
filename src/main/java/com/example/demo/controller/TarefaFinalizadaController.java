package com.example.demo.controller;

import com.example.demo.model.TarefaFinalizada;
import com.example.demo.service.TarefaCanceladaService;
import com.example.demo.service.TarefaFinalizadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefas-finalizadas")
public class TarefaFinalizadaController {

    @Autowired
    TarefaFinalizadaService tarefaFinalizadaService;

    @GetMapping
    public List<TarefaFinalizada> getAllTarefaFinalizadas() {
        return tarefaFinalizadaService.listar();
    }

    @PostMapping
    public TarefaFinalizada criar(@Valid @RequestBody TarefaFinalizada tarefaFinalizada) {
        return tarefaFinalizadaService.criar(tarefaFinalizada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TarefaFinalizada tarefaFinalizada) {
        if (tarefaFinalizadaService.atualizar(id, tarefaFinalizada) == null) {
            String mensagem = "O id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(tarefaFinalizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (tarefaFinalizadaService.delete(id)) {
            String mensagem = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(mensagem);
        }
        String mensagem = "O id informado não existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/{id}")
    public Optional<TarefaFinalizada> buscaPorID(@PathVariable Long id) {
        return tarefaFinalizadaService.buscaPorID(id);
    }
}


