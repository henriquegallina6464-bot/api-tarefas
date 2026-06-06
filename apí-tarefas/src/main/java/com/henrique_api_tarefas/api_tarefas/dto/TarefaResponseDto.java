package com.henrique_api_tarefas.api_tarefas.dto;

import com.henrique_api_tarefas.api_tarefas.model.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponseDto(

        Long id,
        String titulo,
        String descricao,
        StatusTarefa status,
        LocalDateTime dataCriacao,
        LocalDateTime dataConclusao

) {
}
