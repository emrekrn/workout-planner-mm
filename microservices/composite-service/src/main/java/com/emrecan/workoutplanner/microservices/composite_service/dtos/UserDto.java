package com.emrecan.workoutplanner.microservices.composite_service.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private String firstName;
    private String lastName;
}
