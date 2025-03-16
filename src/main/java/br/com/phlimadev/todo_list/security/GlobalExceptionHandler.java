package br.com.phlimadev.todo_list.security;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.phlimadev.todo_list.dto.out.ExceptionResponse;
import br.com.phlimadev.todo_list.exception.IdNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> threatIdNotFoundException(IdNotFoundException exception) {
        ExceptionResponse body = new ExceptionResponse(LocalDateTime.now(), "NOT_FOUND", "404", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
