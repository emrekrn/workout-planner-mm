package com.emrecan.workoutplanner.microservices.composite_service.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String email;
}
