package com.example.todolistapi.todolistapi.service;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.exceptions.RecordNotFoundException;
import com.example.todolistapi.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @Autowired
    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create TODO successfully when everything is ok.")
    void createTodoCase1() {
        TodoDTO todo = new TodoDTO("Tarefa 1", "Descrição 1", false, 1);
        todoService.create(todo);

        verify(todoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should throw exception when TODO name is empty.")
    void createTodoCase2() throws RecordNotFoundException {
        Exception thrown = Assertions.assertThrows(RecordNotFoundException.class, () -> {
            TodoDTO todo = new TodoDTO("", "Descrição 1", false, 1);
            todoService.create(todo);
        });

        assertEquals("O nome não pode ser vazio.", thrown.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when TODO description is empty.")
    void createTodoCase3() throws RecordNotFoundException {
        Exception thrown = Assertions.assertThrows(RecordNotFoundException.class, () -> {
            TodoDTO todo = new TodoDTO("Tarefa 1", "", false, 1);
            todoService.create(todo);
        });

        assertEquals("A descrição não pode ser vazia.", thrown.getMessage());
    }

    @Test
    @DisplayName("Should return all TODOs in DB")
    void findTodoCase1() {
        Todo todo1 = new Todo(1L,"Tarefa 1", "Descrição 1", false, 1);
        Todo todo2 = new Todo(2L,"Tarefa 2", "Descrição 2", false, 2);

        when(todoService.list()).thenReturn(List.of(todo1, todo2));

        List<Todo> result = todoService.list();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0), todo1);
        assertEquals(result.get(1), todo2);

        verify(todoRepository, times(1)).findAll((Sort) any());
    }
}