package com.app.time_tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "time_logs")
public class TimeLog {
    @Id
    private String id;
    private String userId;
    private String date;
    private int startHour;
    private int endHour;
    private String activity;
    // Replaced the boolean with a String to hold 3 states
    private String evaluation;
}