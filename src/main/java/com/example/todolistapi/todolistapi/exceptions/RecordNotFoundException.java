package com.example.todolistapi.todolistapi.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super("A tarefa desejada não existe.");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
