package com.example.todolistapi.todolistapi.controller;

import com.example.todolistapi.todolistapi.dto.TodoRequest;
import com.example.todolistapi.todolistapi.dto.TodoResponse;
import com.example.todolistapi.todolistapi.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest todo) {
        return ResponseEntity.ok().body(todoService.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> list() {
        return ResponseEntity.ok().body(todoService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> listById(@Positive @PathVariable long id) {
        return ResponseEntity.ok().body(todoService.listById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoResponse> update(@Positive @PathVariable long id, @Valid @RequestBody TodoRequest todo) {
        return ResponseEntity.ok().body(todoService.update(id, todo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }
}