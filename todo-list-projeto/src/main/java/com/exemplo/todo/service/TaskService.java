package com.exemplo.todo.service;

import com.exemplo.todo.entity.Task;
import com.exemplo.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task criarTask(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio");
        }
        Task task = new Task();
        task.setTitulo(titulo);
        return repository.save(task);
    }


    public List<Task> listarTodas() {
        return repository.findAll();
    }

    public Task marcarComoConcluida(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (task.isConcluida()) {
            return task;
        }
        task.setConcluida(true);
        return repository.save(task);
    }

    public void excluirTask(Long id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        repository.deleteById(id);
    }

}
