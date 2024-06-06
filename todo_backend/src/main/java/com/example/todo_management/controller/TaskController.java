package com.example.todo_management.controller;

import com.example.todo_management.dto.TaskDTO;
import com.example.todo_management.exception.ResourceNotFoundException;
import com.example.todo_management.service.TaskServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.addTask(taskDTO), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        Optional<List<TaskDTO>>  allTasks =  taskService.getAllTasks();
        if(allTasks.isPresent()) {
            return new ResponseEntity<>(allTasks.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Task not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable long id) {
        Optional<TaskDTO> taskDTO = taskService.getTaskById(id);
        if (taskDTO.isPresent()) {
            return new ResponseEntity<>(taskDTO.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Task not found");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTaskByID(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        Optional<TaskDTO> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Task not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable long id) {
        Optional<TaskDTO> taskDTO = taskService.getTaskById(id);
        if(taskDTO.isPresent()) {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Task not found");
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("status", HttpStatus.NOT_FOUND);
        responseBody.put("error", "Not Found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
