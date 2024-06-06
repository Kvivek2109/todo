package com.example.todo_management.service;

import com.example.todo_management.dto.TaskDTO;
import com.example.todo_management.mapper.TaskDTOMapper;
import com.example.todo_management.model.Task;
import com.example.todo_management.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task savedTask = taskRepository.save(TaskDTOMapper.taskDTOtoTask(taskDTO));
        return TaskDTOMapper.tasktoTaskDTO(savedTask);
    }

    @Override
    public Optional<TaskDTO> getTaskById(long id) {
        try {
            Task task = taskRepository.getReferenceById(id);
            return Optional.of(TaskDTOMapper.tasktoTaskDTO(task));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TaskDTO>> getAllTasks() {
        try {
            List<Task> allTasks = taskRepository.findAll();
            return Optional.of(allTasks.stream().map(TaskDTOMapper::tasktoTaskDTO).toList());
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public TaskDTO updateTask(long id, TaskDTO taskDTO) {
        Task task = taskRepository.getReferenceById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        Task updatedTask = taskRepository.save(task);
        return TaskDTOMapper.tasktoTaskDTO(updatedTask);
    }

    @Override
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}