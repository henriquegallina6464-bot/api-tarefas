package com.henrique_api_tarefas.api_tarefas.controller;

import com.henrique_api_tarefas.api_tarefas.dto.TarefaRequestDto;
import com.henrique_api_tarefas.api_tarefas.dto.TarefaResponseDto;
import com.henrique_api_tarefas.api_tarefas.model.Tarefa;
import com.henrique_api_tarefas.api_tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "Criar uma nova tarefa")
    @PostMapping
    public TarefaResponseDto criar(@RequestBody @Valid TarefaRequestDto dto){
        return tarefaService.criar(dto);
    }

    @Operation(summary = "Listar todas as tarefas ")
    @GetMapping
    public Page<TarefaResponseDto> listarTodas(Pageable  pageable){
        return tarefaService.listarTodos(pageable);
    }

    @Operation(summary = "Busca as terefas por ID")
    @GetMapping("/{id}")
    public TarefaResponseDto buscarPorId(@PathVariable Long id){
        return tarefaService.buscarPorId(id);
    }

    @Operation(summary = "Concluir uma tarefa")
    @PatchMapping("/({id}/concluir")
    public TarefaResponseDto concluir (@PathVariable Long id){
        return tarefaService.concluir(id);
    }

   @Operation(summary = "Deleta uma tarefa")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
         tarefaService.deletar(id);
    }

    @Operation(summary = "Atualiza uma tarefa")
    @PutMapping("/{id}")
    public TarefaResponseDto atualizar(@PathVariable Long id, @RequestBody @Valid TarefaRequestDto dto){
        return tarefaService.atualizar(id, dto);
    }
}
