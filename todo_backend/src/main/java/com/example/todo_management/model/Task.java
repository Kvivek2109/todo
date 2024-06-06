package com.example.todo_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "completed", nullable = false)
    private boolean completed;
}
