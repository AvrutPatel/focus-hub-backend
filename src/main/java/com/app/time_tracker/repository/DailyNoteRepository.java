package com.app.time_tracker.repository;

import com.app.time_tracker.model.DailyNote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DailyNoteRepository extends MongoRepository<DailyNote, String> {
    Optional<DailyNote> findByUserIdAndDate(String userId, String date);
    List<DailyNote> findByUserId(String userId);
}