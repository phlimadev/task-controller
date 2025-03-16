package br.com.phlimadev.todo_list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phlimadev.todo_list.model.StatusEnum;
import br.com.phlimadev.todo_list.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(StatusEnum status);
}
