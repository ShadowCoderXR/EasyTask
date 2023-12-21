package com.easytask.controller;

import com.easytask.model.dto.TaskDto;
import com.easytask.model.entity.Task;
import com.easytask.model.payload.MessageResponse;
import com.easytask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
@Transactional()
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TaskDto taskDto) {
        try {
            Task task = taskService.save(taskDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task created successfully")
                    .object(task)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task not created")
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TaskDto taskDto, @PathVariable Long id) {
        try {
            Task taskUpdate = taskService.update(id, taskDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task updated successfully")
                    .object(taskUpdate)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task not updated")
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task not found")
                    .object(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Task taskDelete = taskService.findById(id);
            taskService.delete(taskDelete);
            return new ResponseEntity<>(taskDelete, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Task not deleted")
                    .object(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }
}
