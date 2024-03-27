package com.example.todolistapi.todolistapi.mapper;

import com.example.todolistapi.todolistapi.dto.TodoRequest;
import com.example.todolistapi.todolistapi.dto.TodoResponse;
import com.example.todolistapi.todolistapi.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoResponse toDTO(Todo todo) {
        if (todo == null) return null;

        return new TodoResponse(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getRealizada(), todo.getPrioridade());
    }

    public Todo toEntity(TodoRequest todoRequest) {
        if (todoRequest == null) return null;

        Todo newTodo = new Todo();
        newTodo.setNome(todoRequest.nome());
        newTodo.setDescricao(todoRequest.descricao());
        newTodo.setRealizada(todoRequest.realizada());
        newTodo.setPrioridade(todoRequest.prioridade());

        return newTodo;
    }
}
