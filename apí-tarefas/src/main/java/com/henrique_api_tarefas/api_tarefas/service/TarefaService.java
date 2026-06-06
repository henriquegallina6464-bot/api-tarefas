package com.henrique_api_tarefas.api_tarefas.service;

import com.henrique_api_tarefas.api_tarefas.dto.TarefaRequestDto;
import com.henrique_api_tarefas.api_tarefas.dto.TarefaResponseDto;
import com.henrique_api_tarefas.api_tarefas.dto.TarefasStatusRequestDto;
import com.henrique_api_tarefas.api_tarefas.exception.ResourceNotFoundException;
import com.henrique_api_tarefas.api_tarefas.model.StatusTarefa;
import com.henrique_api_tarefas.api_tarefas.model.Tarefa;
import com.henrique_api_tarefas.api_tarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaResponseDto criar(TarefaRequestDto dto){
        Tarefa tarefa = Tarefa.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .status(StatusTarefa.PENDENTE)
                .dataCriacao(LocalDateTime.now())
                .build();

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        return toResponseDto(tarefaSalva);
    }

    public Page<TarefaResponseDto> listarTodos(Pageable pageable) {
        return tarefaRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    public TarefaResponseDto buscarPorId(Long id){
        Tarefa tarefa = buscarEntidadePorId(id);
        return toResponseDto(tarefa);
    }

    public TarefaResponseDto concluir(Long id){
        Tarefa tarefa = buscarEntidadePorId(id);
        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        tarefa.setDataConclusao(LocalDateTime.now());

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
        return toResponseDto(tarefaAtualizada);
    }

    public void deletar(Long id){
        Tarefa tarefa = buscarEntidadePorId(id);
        tarefaRepository.delete(tarefa);
    }

    public TarefaResponseDto atualizar(Long id, TarefaRequestDto dto){
        Tarefa tarefa = buscarEntidadePorId(id);

        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);

        return toResponseDto(tarefaAtualizada);
    }

    private Tarefa buscarEntidadePorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
    }

    private TarefaResponseDto toResponseDto(Tarefa tarefa) {
        return new TarefaResponseDto(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getDataCriacao(),
                tarefa.getDataConclusao()
        );
    }


    public TarefaResponseDto atualizarStatus(Long id, TarefasStatusRequestDto dto){
        Tarefa tarefa = buscarEntidadePorId(id);

        tarefa.setStatus(dto.status());

        if (dto.status() == StatusTarefa.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
        } else {
            tarefa.setDataConclusao(null);
        }

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);

        return toResponseDto(tarefaAtualizada);
    }

    public List<TarefaResponseDto> buscarPorStatus(StatusTarefa status) {
        return tarefaRepository.findByStatus(status)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public List<TarefaResponseDto> buscarPorTitulo(String titulo) {
        return tarefaRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }
}
