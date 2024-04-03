package com.example.demo.controller;

import com.example.demo.model.TarefaCancelada;
import com.example.demo.service.TarefaCanceladaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas-canceladas")
public class TarefaCanceladaController {

    @Autowired
    TarefaCanceladaService tarefaCanceladaService;

    @GetMapping
    public List<TarefaCancelada> getAllTarefaCanceladas(){
        return tarefaCanceladaService.listar();
    }

    @PostMapping
    public TarefaCancelada criar(@Valid @RequestBody TarefaCancelada tarefaCancelada){
        return tarefaCanceladaService.criar(tarefaCancelada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaCancelada tarefaCancelada) {
        Optional<TarefaCancelada> tarefaOptional = tarefaCanceladaService.buscaPorID(id);
        if (!tarefaOptional.isPresent()) {
            String mensagem = "O id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

        TarefaCancelada tarefaExistente = tarefaOptional.get();
        tarefaExistente.setMotivoCancelamento(tarefaCancelada.getMotivoCancelamento());

        TarefaCancelada tarefaAtualizada = tarefaCanceladaService.atualizar(id, tarefaExistente);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(tarefaCanceladaService.delete(id)) {
            String mensagem = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(mensagem);
        }
        String mensagem = "O id informado não existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/{id}")
    public Optional<TarefaCancelada> buscaporID(@PathVariable Long id){
        return this.tarefaCanceladaService.buscaPorID(id);
    }

}
