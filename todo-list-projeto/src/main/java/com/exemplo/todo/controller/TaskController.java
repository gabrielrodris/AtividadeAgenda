package com.exemplo.todo.controller;

import com.exemplo.todo.entity.Task;
import com.exemplo.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task criar(@RequestParam String titulo) {
        return service.criarTask(titulo);
    }

    @GetMapping
    public List<Task> listar() {
        return service.listarTodas();
    }

    @PutMapping("/{id}/concluir")
    public Task concluir(@PathVariable Long id) {
        return service.marcarComoConcluida(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluirTask(id);
    }
}
