package com.easytask.model.dto;

import com.easytask.model.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@ToString
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Date expiration_date;
    private Date created_at;
}
