package br.com.phlimadev.todo_list.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phlimadev.todo_list.dto.in.TaskRequest;
import br.com.phlimadev.todo_list.dto.out.TaskResponse;
import br.com.phlimadev.todo_list.exception.IdNotFoundException;
import br.com.phlimadev.todo_list.model.StatusEnum;
import br.com.phlimadev.todo_list.model.Task;
import br.com.phlimadev.todo_list.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public TaskResponse registerTask(TaskRequest task) {
        Task newTask = new Task(task);
        repository.save(newTask);
        return new TaskResponse(newTask);
    }

    public List<TaskResponse> findAll() {
        List<TaskResponse> allTasks = repository.findAll().stream().map(TaskResponse::new).toList();
        return allTasks;
    }

    public TaskResponse findById(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new IdNotFoundException("Id " + id + " not found."));
        return new TaskResponse(task);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        Task task = repository.findById(id).orElseThrow(() -> new IdNotFoundException("Id " + id + " not found."));
        if (updatedTask.title() != null) {
            task.setTitle(updatedTask.title());
        }
        if (updatedTask.description() != null) {
            task.setDescription(updatedTask.description());
        }
        if (updatedTask.status() != null) {
            task.setStatus(updatedTask.status());
        }
        if (updatedTask.priority() != null) {
            task.setPriority(updatedTask.priority());
        }
        if (updatedTask.startDate() != null) {
            task.setStartDate(updatedTask.startDate());
        }
        if (updatedTask.endDate() != null) {
            task.setEndDate(updatedTask.endDate());
        }
        repository.save(task);
        return new TaskResponse(task);
    }

    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new IdNotFoundException("Id " + id + " not found.");
        }
        repository.deleteById(id);
    }

    public List<TaskResponse> findLateTasks() {
        List<TaskResponse> lateTasks = repository
        .findAll()
        .stream()
        .filter(task -> LocalDate.now().isAfter(task.getEndDate()) && task.getStatus() != StatusEnum.COMPLETED)
        .map(TaskResponse::new)
        .toList();
        return lateTasks;
    }

    public List<TaskResponse> findByStatus(StatusEnum status) {
        List<TaskResponse> tasks = repository.findByStatus(status).stream().map(TaskResponse::new).toList();
        return tasks;
    }
}