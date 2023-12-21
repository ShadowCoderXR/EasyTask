package com.easytask.service;

import com.easytask.model.dao.UserDao;
import com.easytask.model.dto.UserDto;
import com.easytask.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        return userDao.save(user);
    }

    @Override
    public User update(Long id, UserDto userDto) {
        User user = userDao.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            return userDao.save(user);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
