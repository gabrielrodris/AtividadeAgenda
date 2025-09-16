package com.exemplo.todo.service;

import com.exemplo.todo.entity.Task;
import com.exemplo.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceIntegrationTest {
    @Autowired
    private TaskService service;

    @Autowired
    private TaskRepository repository;

    @Test
    void testeCrudTask(){
        //criar task
        Task task = new Task();

        Task criada = service.criarTask("Estudar Spring");

        assertNotNull(criada);
        assertEquals("Estudar Spring", criada.getTitulo());
        verify(repository, times(1)).save(any(Task.class));

    }
}
