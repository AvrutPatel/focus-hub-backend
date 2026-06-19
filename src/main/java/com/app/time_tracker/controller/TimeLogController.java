package com.app.time_tracker.controller;

import com.app.time_tracker.model.TimeLog;
import com.app.time_tracker.repository.TimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-logs")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TimeLogController {

    private final TimeLogRepository timeLogRepository;

    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<TimeLog>> getLogsForDate(@PathVariable String userId, @PathVariable String date) {
        return ResponseEntity.ok(timeLogRepository.findByUserIdAndDateOrderByStartHourAsc(userId, date));
    }

    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody TimeLog log) {
        // Basic validation to prevent overlapping could go here in a production app
        return ResponseEntity.ok(timeLogRepository.save(log));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable String id) {
        timeLogRepository.deleteById(id);
        return ResponseEntity.ok("Log deleted");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TimeLog>> getAllUserLogs(@PathVariable String userId) {
        return ResponseEntity.ok(timeLogRepository.findByUserId(userId));
    }

    @DeleteMapping("/user/{userId}/date/{date}")
    public ResponseEntity<?> deleteLogsForDate(@PathVariable String userId, @PathVariable String date) {
        timeLogRepository.deleteByUserIdAndDate(userId, date);
        return ResponseEntity.ok("Deleted logs for day");
    }

    @DeleteMapping("/user/{userId}/cleanup/{date}")
    public ResponseEntity<?> cleanupOldLogs(@PathVariable String userId, @PathVariable String date) {
        timeLogRepository.deleteByUserIdAndDateLessThan(userId, date);
        return ResponseEntity.ok("Cleaned up old logs");
    }
}