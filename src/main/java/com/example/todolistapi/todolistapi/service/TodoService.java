package com.example.todolistapi.todolistapi.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.repository.TodoRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(long id, Todo todo) {
        todo.setId(id);
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(long id) {
        todoRepository.deleteById(id);
        return list();
    }
}