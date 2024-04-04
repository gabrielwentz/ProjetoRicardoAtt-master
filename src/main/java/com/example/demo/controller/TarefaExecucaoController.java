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
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaExecucao tarefaExecucao) {
        // Verifica se o atributo descricaoTarefa é nulo
        if (tarefaExecucao.getDescricaoTarefa() == null) {
            // Retorna um erro informando que o atributo descricaoTarefa não pode ser nulo
            String mensagem = "O atributo 'descricaoTarefa' não pode ser nulo.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }

        Optional<TarefaExecucao> tarefaOptional = tarefaExecucaoService.buscaPorID(id);

        if (!tarefaOptional.isPresent()) {
            String mensagem = "O id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

        TarefaExecucao tarefaExistente = tarefaOptional.get();

        // Atualiza os atributos da entidade com os valores da requisição
        tarefaExistente.setDataInicio(tarefaExecucao.getDataInicio());
        tarefaExistente.setDataFim(tarefaExecucao.getDataFim());
        tarefaExistente.setDescricaoTarefa(tarefaExecucao.getDescricaoTarefa());

        // Realiza a atualização da entidade
        tarefaExecucaoService.atualizar(id, tarefaExistente);

        return ResponseEntity.ok(tarefaExistente);
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

    @GetMapping("/contagem")
    public ResponseEntity<Long> contarTarefasExecucao() {
        long totalTarefasFinalizadas = tarefaExecucaoService.contarTarefasExecucao();
        return ResponseEntity.ok(totalTarefasFinalizadas);
    }


}

