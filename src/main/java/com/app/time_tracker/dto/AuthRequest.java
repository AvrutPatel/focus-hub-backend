package com.app.time_tracker.dto;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String username; // Only used for registration
}
