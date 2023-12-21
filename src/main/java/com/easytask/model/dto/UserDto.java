package com.easytask.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date created_at;
}
