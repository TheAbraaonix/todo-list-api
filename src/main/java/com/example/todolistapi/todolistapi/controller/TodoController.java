package com.example.todolistapi.todolistapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public List<Todo> create(@RequestBody TodoDTO todo) {
        return todoService.create(todo);
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    @PutMapping("{id}")
    public List<Todo> update(@PathVariable long id, @RequestBody TodoDTO todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("{id}")
    public List<Todo> delete(@PathVariable("id") long id) {
        return todoService.delete(id);
    }
}