package com.example.todolistapi.todolistapi.controller;

import com.example.todolistapi.todolistapi.dto.TodoDTO;
import com.example.todolistapi.todolistapi.entity.Todo;
import com.example.todolistapi.todolistapi.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@Valid @RequestBody TodoDTO todo) {
        return ResponseEntity.ok().body(todoService.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.ok().body(todoService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Todo>> listById(@Positive @PathVariable long id) {
        return ResponseEntity.ok().body(todoService.listById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<List<Todo>> update(@Positive @PathVariable long id, @Valid @RequestBody TodoDTO todo) {
        return ResponseEntity.ok().body(todoService.update(id, todo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }
}