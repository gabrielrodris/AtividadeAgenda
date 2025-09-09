package com.exemplo.todo.service;

import com.exemplo.todo.entity.Task;
import com.exemplo.todo.repository.TaskRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarUmaTask() {
        Task task = new Task(1L, "Estudar Spring", false);
        when(repository.save(any(Task.class))).thenReturn(task);

        Task criada = service.criarTask("Estudar Spring");

        assertNotNull(criada);
        assertEquals("Estudar Spring", criada.getTitulo());
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void deveMarcarTaskComoConcluida() {
        Task task = new Task(1L, "Estudar Spring", false);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(task)).thenReturn(new Task(1L, "Estudar Spring", true));

        Task concluida = service.marcarComoConcluida(1L);

        assertTrue(concluida.isConcluida());
    }

    @Test
    void deveLancarExcecaoQuandoTaskNaoEncontrada() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.marcarComoConcluida(99L));
    }
}
