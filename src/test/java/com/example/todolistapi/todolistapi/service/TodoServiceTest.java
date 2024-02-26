package com.example.todolistapi.todolistapi.service;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void createTodoCase2() {
    }
}