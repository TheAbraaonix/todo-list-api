package com.example.todolistapi.todolistapi.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.todolistapi.todolistapi.exceptions.BlankDescriptionException;
import com.example.todolistapi.todolistapi.exceptions.BlankNameException;
import com.example.todolistapi.todolistapi.exceptions.TodoNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BlankNameException.class)
    private ResponseEntity<RestErrorMessage> BlankNameHandler(BlankNameException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BlankDescriptionException.class)
    private ResponseEntity<RestErrorMessage> BlankDescriptionHandler(BlankDescriptionException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    private ResponseEntity<RestErrorMessage> NotFoundTodoHandler(TodoNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
