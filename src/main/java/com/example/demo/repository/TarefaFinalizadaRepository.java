package com.example.demo.repository;

import com.example.demo.model.TarefaFinalizada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaFinalizadaRepository extends JpaRepository<TarefaFinalizada, Long> {
}
