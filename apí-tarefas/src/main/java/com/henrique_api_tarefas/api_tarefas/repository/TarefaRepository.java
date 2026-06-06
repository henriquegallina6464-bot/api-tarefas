package com.henrique_api_tarefas.api_tarefas.repository;

import com.henrique_api_tarefas.api_tarefas.model.StatusTarefa;
import com.henrique_api_tarefas.api_tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository  extends JpaRepository<Tarefa,Long> {

    List<Tarefa> findByStatus(StatusTarefa status);

    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);

}
