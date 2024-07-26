package com.emrecan.workoutplanner.microservices.user.persistence;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
}
