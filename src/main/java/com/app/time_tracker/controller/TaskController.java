package com.app.time_tracker.controller;

import com.app.time_tracker.model.Task;
import com.app.time_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable String userId) {
        return ResponseEntity.ok(taskRepository.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setStatus("PENDING");
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleTaskStatus(@PathVariable String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(task.getStatus().equals("PENDING") ? "COMPLETED" : "PENDING");
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok("Task deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setType(updatedTask.getType());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());
        task.setHeading(updatedTask.getHeading());
        task.setAchievement(updatedTask.isAchievement());

        return ResponseEntity.ok(taskRepository.save(task));
    }
}