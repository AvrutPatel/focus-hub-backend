package com.app.time_tracker.controller;

import com.app.time_tracker.model.DailyNote;
import com.app.time_tracker.repository.DailyNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DailyNoteController {

    private final DailyNoteRepository dailyNoteRepository;

    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<DailyNote> getNote(@PathVariable String userId, @PathVariable String date) {
        return dailyNoteRepository.findByUserIdAndDate(userId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<DailyNote> saveNote(@RequestBody DailyNote note) {
        // If a note already exists for this day, update it. Otherwise create new.
        DailyNote existing = dailyNoteRepository.findByUserIdAndDate(note.getUserId(), note.getDate()).orElse(null);
        if (existing != null) {
            existing.setContent(note.getContent());
            return ResponseEntity.ok(dailyNoteRepository.save(existing));
        }
        return ResponseEntity.ok(dailyNoteRepository.save(note));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DailyNote>> getAllNotes(@PathVariable String userId) {
        return ResponseEntity.ok(dailyNoteRepository.findByUserId(userId));
    }
}