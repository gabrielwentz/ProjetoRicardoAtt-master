package com.example.demo.controller;

import com.example.demo.model.TiposTarefas;
import com.example.demo.service.TiposTarefasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TiposTarefasController {

    @Autowired
    TiposTarefasService tiposTarefasService;

    @GetMapping
    public List<TiposTarefas> getAllTiposTarefas(){
        return tiposTarefasService.listar();
    }

    @PostMapping
    public TiposTarefas criar(@Valid @RequestBody TiposTarefas tiposTarefas){
        return this.tiposTarefasService.criar(tiposTarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody TiposTarefas tiposTarefas){
        if (tiposTarefasService.atualizar(id, tiposTarefas) == null) {
            String mensagem = "O id não existe no banco de dados.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(tiposTarefas);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@PathVariable Long id) {
        if (tiposTarefasService.delete(id)){
            String mensagem = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(mensagem);
        }
        String mensagem = "O id não existe no banco de dados.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);

    }

    @GetMapping("/{id}")
    public Optional<TiposTarefas> buscaPorID(@PathVariable Long id){
        return tiposTarefasService.buscaPorID(id);
    }

}
