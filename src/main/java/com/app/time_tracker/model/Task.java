package com.app.time_tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String userId; // Links the task to the logged-in user
    private String title;
    private String type; // DAILY, WEEKLY, MONTHLY, LONG_TERM, NO_DUE_DATE
    private String status; // PENDING, COMPLETED
    private String dueDate; // Format: YYYY-MM-DD
    private String heading; // Used only for NO_DUE_DATE tasks
    private boolean achievement;
}