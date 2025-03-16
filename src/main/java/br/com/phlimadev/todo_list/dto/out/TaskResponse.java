package br.com.phlimadev.todo_list.dto.out;

import java.time.LocalDate;

import br.com.phlimadev.todo_list.model.PriorityEnum;
import br.com.phlimadev.todo_list.model.StatusEnum;
import br.com.phlimadev.todo_list.model.Task;

public record TaskResponse(Long id, String title, String description, StatusEnum status, PriorityEnum priority, LocalDate startDate, LocalDate endDate) {
    public TaskResponse(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getStartDate(), task.getEndDate());
    }
}
