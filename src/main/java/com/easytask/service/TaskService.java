package com.easytask.service;

import com.easytask.model.dto.TaskDto;
import com.easytask.model.entity.Task;

import java.util.List;

public interface TaskService {
    Task save(TaskDto task);
    Task update(Long id, TaskDto task);
    Task findById(Long id);
    List<Task> findAll();
    void delete(Task task);
}
