package com.example.todolistapi.todolistapi.exceptions;

public class BlankNameException extends RuntimeException {
    public BlankNameException() {
        super("O nome não pode ser vazio.");
    }

    public BlankNameException(String message) {
        super(message);
    }
}
