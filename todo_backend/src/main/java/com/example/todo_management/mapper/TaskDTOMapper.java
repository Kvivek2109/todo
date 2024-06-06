package com.example.todo_management.mapper;

import com.example.todo_management.dto.TaskDTO;
import com.example.todo_management.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskDTOMapper {

    public static TaskDTO tasktoTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());
        return taskDTO;
    }

    public static Task taskDTOtoTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        return task;
    }
}
