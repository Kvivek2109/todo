package com.example.todo_management.service;

import com.example.todo_management.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskDTO addTask(TaskDTO taskDTO);
    Optional<TaskDTO> getTaskById(long id);
    Optional<List<TaskDTO>> getAllTasks();
    TaskDTO updateTask(long id, TaskDTO taskDTO);
    void deleteTaskById(long id);
}
