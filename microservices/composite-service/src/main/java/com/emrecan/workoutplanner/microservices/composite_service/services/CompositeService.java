package com.emrecan.workoutplanner.microservices.composite_service.services;

import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;

public interface CompositeService {

    String loginUser(LoginRequest loginRequest);
    String createUser(UserDto userDto);
    boolean isTokenValid (String token);
}
