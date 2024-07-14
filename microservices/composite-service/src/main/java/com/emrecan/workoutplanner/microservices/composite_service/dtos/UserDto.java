package com.emrecan.workoutplanner.microservices.composite_service.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
