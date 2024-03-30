package com.example.todolistapi.todolistapi.service;

import com.example.todolistapi.todolistapi.dto.TodoRequest;
import com.example.todolistapi.todolistapi.dto.TodoResponse;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.exceptions.RecordNotFoundException;
import com.example.todolistapi.todolistapi.mapper.TodoMapper;
import com.example.todolistapi.todolistapi.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoMapper todoMapper;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create TODO successfully when everything is ok.")
    void createTodoCase1() {
        // Mock do mapeamento de Todo para TodoResponse
        when(todoMapper.toDTO(any(Todo.class)))
                .thenAnswer(invocation -> {
                    Todo todo = invocation.getArgument(0);
                    return new TodoResponse(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getRealizada(), todo.getPrioridade());
                });

        TodoRequest todoRequest = new TodoRequest("Tarefa 1", "Descrição 1", false, 1);

        // When
        TodoResponse todoResponse = todoService.create(todoRequest);

        // Then
        Assertions.assertNotNull(todoResponse);
        Assertions.assertNotNull(todoResponse.id());
        assertEquals("Tarefa 1", todoResponse.nome());
        assertEquals("Descrição 1", todoResponse.descricao());
        assertFalse(todoResponse.realizada());
        assertEquals(1, todoResponse.prioridade());

        // Verifica se os métodos do Mapper foram chamados corretamente
        verify(todoMapper, times(1)).toDTO(any(Todo.class));

        // Verifica se o método save do repository foi chamado
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    @DisplayName("Should throw exception when TODO name is empty.")
    void createTodoCase2() throws ConstraintViolationException {
        TodoRequest todo = new TodoRequest("", "Descrição 1", false, 1);

        doThrow(ConstraintViolationException.class).when(todoRepository).save(any(Todo.class));

        // Verificando se a ConstraintViolationException é lançada
        Assertions.assertThrows(ConstraintViolationException.class, () -> todoService.create(todo));
    }

    @Test
    @DisplayName("Should throw exception when TODO description is empty.")
    void createTodoCase3() throws RecordNotFoundException {
        TodoRequest todo = new TodoRequest("Tarefa 1", "", false, 1);

        doThrow(ConstraintViolationException.class).when(todoRepository).save(any(Todo.class));

        // Verificando se a ConstraintViolationException é lançada
        Assertions.assertThrows(ConstraintViolationException.class, () -> todoService.create(todo));
    }

    @Test
    @DisplayName("Should return all TODOs in DB")
    void findTodoCase1() {
        // Mock dos dados de retorno do repository
        Todo todo1 = new Todo("Tarefa 1", "Descrição 1", false, 1);
        Todo todo2 = new Todo("Tarefa 2", "Descrição 2", false, 2);
        List<Todo> todos = List.of(todo1, todo2);

        // Mock dos dados de retorno mapeados para TodoResponse
        TodoResponse todoResponse1 = new TodoResponse(1L, "Tarefa 1", "Descrição 1", false, 1);
        TodoResponse todoResponse2 = new TodoResponse(2L, "Tarefa 2", "Descrição 2", false, 2);

        // Mock do método findAll do todoRepository com qualquer Sort
        when(todoRepository.findAll(any(Sort.class))).thenReturn(todos);

        // Mock do mapeamento de Todo para TodoResponse
        when(todoMapper.toDTO(todo1)).thenReturn(todoResponse1);
        when(todoMapper.toDTO(todo2)).thenReturn(todoResponse2);

        List<TodoResponse> result = todoService.list();

        // Verifica se o método findAll do repository foi chamado uma vez
        verify(todoRepository, times(1)).findAll(any(Sort.class));

        // Verifica se o método toDTO do todoMapper foi chamado para cada Todo
        verify(todoMapper, times(1)).toDTO(todo1);
        verify(todoMapper, times(1)).toDTO(todo2);

        // Verifica se o tamanho da lista e os elementos estão corretos
        assertEquals(2, result.size());
        assertEquals(todoResponse1, result.get(0));
        assertEquals(todoResponse2, result.get(1));
    }
}