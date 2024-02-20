package com.example.todolistapi.todolistapi.exceptions;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException() {
        super("A tarefa desejada não existe.");
    }

    public TodoNotFoundException(String message) {
        super(message);
    }
}
