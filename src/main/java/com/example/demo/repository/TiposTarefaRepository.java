package com.example.demo.repository;

import com.example.demo.model.TiposTarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TiposTarefaRepository extends JpaRepository<TiposTarefas, Long> {

}
