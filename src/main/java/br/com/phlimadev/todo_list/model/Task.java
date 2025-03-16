package br.com.phlimadev.todo_list.model;

import java.time.LocalDate;

import br.com.phlimadev.todo_list.dto.in.TaskRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "task_description")
    private String description;
    private StatusEnum status;
    private PriorityEnum priority;
    private LocalDate startDate;
    private LocalDate endDate;

    public Task(TaskRequest newTask) {
        this.title = newTask.title();
        this.description = newTask.description();
        this.status = newTask.status();
        this.priority = newTask.priority();
        this.startDate = newTask.startDate();
        this.endDate = newTask.endDate();
    }
}
