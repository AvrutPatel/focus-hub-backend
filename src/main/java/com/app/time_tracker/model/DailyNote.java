package com.app.time_tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "daily_notes")
public class DailyNote {
    @Id
    private String id;
    private String userId;
    private String date; // YYYY-MM-DD
    private String content;
}