package com.henrique_api_tarefas.api_tarefas.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        String path
) {
}
