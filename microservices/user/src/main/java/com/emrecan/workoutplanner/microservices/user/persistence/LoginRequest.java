package com.emrecan.workoutplanner.microservices.user.persistence;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
