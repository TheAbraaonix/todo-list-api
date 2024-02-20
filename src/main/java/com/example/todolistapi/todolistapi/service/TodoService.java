package com.example.todolistapi.todolistapi.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.exceptions.BlankDescriptionException;
import com.example.todolistapi.todolistapi.exceptions.BlankNameException;
import com.example.todolistapi.todolistapi.repository.TodoRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(TodoDTO todo) {
        Todo newTodo = new Todo(todo);

        if (newTodo.getNome().length() == 0) {
            throw new BlankNameException();
        }

        if (newTodo.getDescricao().length() == 0) {
            throw new BlankDescriptionException();
        }

        todoRepository.save(newTodo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(long id, TodoDTO todo) {
        Todo updatedTodo = new Todo(todo);

        if (updatedTodo.getNome().length() == 0) {
            throw new BlankNameException();
        }

        if (updatedTodo.getDescricao().length() == 0) {
            throw new BlankDescriptionException();
        }

        updatedTodo.setId(id);
        todoRepository.save(updatedTodo);
        return list();
    }

    public List<Todo> delete(long id) {
        todoRepository.deleteById(id);
        return list();
    }
}