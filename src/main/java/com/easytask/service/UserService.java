package com.easytask.service;

import com.easytask.model.dto.UserDto;
import com.easytask.model.entity.User;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    User update(Long id, UserDto user);
    User findById(Long id);
    List<User> findAll();
    void delete(User user);
}
