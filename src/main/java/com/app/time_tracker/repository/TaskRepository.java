package com.app.time_tracker.repository;

import com.app.time_tracker.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId);
    void deleteByUserIdAndTypeAndDueDateLessThan(String userId, String type, String date);
}