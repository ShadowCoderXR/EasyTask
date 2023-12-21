package com.easytask.model.dao;

import com.easytask.model.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, Long> {
}
