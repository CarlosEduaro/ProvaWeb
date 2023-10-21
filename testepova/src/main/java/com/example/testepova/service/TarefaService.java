package com.example.testepova.service;

import com.example.testepova.entity.Tarefa;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TarefaService {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setId(proximoId++);
        tarefas.add(tarefa);
        return tarefa;
    }

    public Tarefa buscarTarefa(Long id) {
        Optional<Tarefa> tarefa = tarefas.stream().filter(e -> e.getId().equals(id)).findFirst();
        return tarefa.orElse(null);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = tarefas.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setNome(tarefaAtualizada.getNome());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            return tarefa;
        }
        return null;
    }

    public void removerTarefa(Long id) {
        tarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }
}
