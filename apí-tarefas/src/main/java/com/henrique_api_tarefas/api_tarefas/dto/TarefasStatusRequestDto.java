package com.henrique_api_tarefas.api_tarefas.dto;

import com.henrique_api_tarefas.api_tarefas.model.StatusTarefa;
import jakarta.validation.constraints.NotNull;

public record TarefasStatusRequestDto(

        @NotNull(message = "O status é obrigatório")
        StatusTarefa status
) {
}
