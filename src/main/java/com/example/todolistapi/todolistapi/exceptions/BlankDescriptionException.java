package com.example.todolistapi.todolistapi.exceptions;

public class BlankDescriptionException extends RuntimeException {
    public BlankDescriptionException() {
        super("A descrição não pode ser vazia.");
    }

    public BlankDescriptionException(String message) {
        super(message);
    }
}
