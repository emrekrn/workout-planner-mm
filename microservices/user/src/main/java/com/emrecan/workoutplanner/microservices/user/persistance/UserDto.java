package com.emrecan.workoutplanner.microservices.user.persistance;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
}
