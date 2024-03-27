package com.example.todolistapi.todolistapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.exceptions.RecordNotFoundException;
import com.example.todolistapi.todolistapi.repository.TodoRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(TodoDTO todo) {
        Todo newTodo = new Todo(todo);
        todoRepository.save(newTodo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        return todoRepository.findAll(sort);
    }

    public Optional<Todo> listById(long id) {
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return todo;
    }

    public List<Todo> update(long id, TodoDTO todo) {
        todoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException());
        Todo updatedTodo = new Todo(todo);
        updatedTodo.setId(id);
        todoRepository.save(updatedTodo);
        return list();
    }

    public void delete(long id) {
        Todo deleteTodo = todoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException());
        todoRepository.delete(deleteTodo);
    }
}