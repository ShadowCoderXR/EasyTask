package com.easytask.controller;

import com.easytask.model.dto.UserDto;
import com.easytask.model.entity.User;
import com.easytask.model.payload.MessageResponse;
import com.easytask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Transactional()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        try {
            User user = userService.save(userDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User created successfully")
                    .object(user)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User not created")
                    .object(null)
                    .build(),
            HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        try {
            User userUpdate = userService.update(id, userDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User updated successfully")
                    .object(userUpdate)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User not updated")
                    .object(null)
                    .build(),
            HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User not found")
                    .object(null)
                    .build(),
            HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            User userDelete = userService.findById(id);
            userService.delete(userDelete);
            return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User not found")
                    .object(null)
                    .build(),
            HttpStatus.NOT_FOUND);
        }
    }
}
