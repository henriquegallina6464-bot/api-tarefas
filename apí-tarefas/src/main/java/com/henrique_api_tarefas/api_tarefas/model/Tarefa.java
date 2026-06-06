package com.henrique_api_tarefas.api_tarefas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarefa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String titulo;
   private String descricao;
   private StatusTarefa status;
   private LocalDateTime dataCriacao;
   private LocalDateTime dataConclusao;

   public void prePersist() {
      this.dataCriacao = LocalDateTime.now();

      if (this.status == null){
         this.status = StatusTarefa.PENDENTE;
      }
   }
}
