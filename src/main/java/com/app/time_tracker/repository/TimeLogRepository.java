package com.app.time_tracker.repository;

import com.app.time_tracker.model.TimeLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TimeLogRepository extends MongoRepository<TimeLog, String> {
    List<TimeLog> findByUserIdAndDateOrderByStartHourAsc(String userId, String date);
    List<TimeLog> findByUserId(String userId);
    void deleteByUserIdAndDate(String userId, String date);
    void deleteByUserIdAndDateLessThan(String userId, String date);
}