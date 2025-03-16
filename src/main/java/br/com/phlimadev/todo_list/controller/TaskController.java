package br.com.phlimadev.todo_list.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.phlimadev.todo_list.dto.in.TaskRequest;
import br.com.phlimadev.todo_list.dto.out.TaskResponse;
import br.com.phlimadev.todo_list.model.StatusEnum;
import br.com.phlimadev.todo_list.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> finAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> registerTask(@RequestBody @Valid TaskRequest newTask) {
        return new ResponseEntity<>(taskService.registerTask(newTask), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id, @RequestBody TaskRequest updateTask) {
        return new ResponseEntity<>(taskService.updateTask(id, updateTask), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/late")
    public ResponseEntity<List<TaskResponse>> findLateTasks() {
        return new ResponseEntity<>(taskService.findLateTasks(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> findByStatus(@RequestParam StatusEnum status) {
        return new ResponseEntity<>(taskService.findByStatus(status), HttpStatus.OK);
    }
}