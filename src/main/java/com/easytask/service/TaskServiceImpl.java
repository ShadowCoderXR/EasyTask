package com.easytask.service;

import com.easytask.model.dao.TaskDao;
import com.easytask.model.dto.TaskDto;
import com.easytask.model.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task save(TaskDto taskDto) {
        Task task = Task.builder()
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .expiration_date(taskDto.getExpiration_date())
                .build();
        return taskDao.save(task);
    }

    @Override
    public Task update(Long id, TaskDto taskDto) {
        Task task = taskDao.findById(id).orElse(null);
        if (task != null) {
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setExpiration_date(taskDto.getExpiration_date());
            return taskDao.save(task);
        }
        return null;
    }

    @Override
    public Task findById(Long id) {
        return taskDao.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return (List<Task>) taskDao.findAll();
    }

    @Override
    public void delete(Task task) {
        taskDao.delete(task);
    }
}
