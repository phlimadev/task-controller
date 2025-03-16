package br.com.phlimadev.todo_list.dto.in;

import java.time.LocalDate;

import br.com.phlimadev.todo_list.model.PriorityEnum;
import br.com.phlimadev.todo_list.model.StatusEnum;

public record TaskRequest(String title, String description, StatusEnum status, PriorityEnum priority, LocalDate startDate, LocalDate endDate) {
    
}
