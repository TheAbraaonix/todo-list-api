package com.example.todolistapi.todolistapi.service;

import com.example.todolistapi.todolistapi.dto.TodoRequest;
import com.example.todolistapi.todolistapi.dto.TodoResponse;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.exceptions.RecordNotFoundException;
import com.example.todolistapi.todolistapi.mapper.TodoMapper;
import com.example.todolistapi.todolistapi.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoResponse> create(TodoRequest todo) {
        Todo newTodo = new Todo(todo);
        todoRepository.save(newTodo);
        return list();
    }

    public List<TodoResponse> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        List<Todo> todos = todoRepository.findAll(sort);

        return todos.stream()
                .map(todoMapper::toDTO)
                .toList();
    }

    public TodoResponse listById(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        return todoMapper.toDTO(todo);
    }

    public List<TodoResponse> update(long id, TodoRequest todo) {
        todoRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        Todo updatedTodo = new Todo(todo);
        updatedTodo.setId(id);
        todoRepository.save(updatedTodo);
        return list();
    }

    public void delete(long id) {
        Todo deleteTodo = todoRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        todoRepository.delete(deleteTodo);
    }
}