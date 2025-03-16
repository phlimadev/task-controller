package br.com.phlimadev.todo_list.dto.out;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String error, String code, String message) {
    
}
