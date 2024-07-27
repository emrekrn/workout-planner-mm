package com.emrecan.workoutplanner.microservices.composite_service.services;

import com.emrecan.workoutplanner.exceptions.UserConflictException;
import com.emrecan.workoutplanner.exceptions.UserNotFoundException;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;

public interface UserService {

    String loginUser(LoginRequest loginRequest);
    String createUser(UserDto userDto) throws UserConflictException;
    UserDto getUserByUsername(String username) throws UserNotFoundException;
    UserDto updateUser(String username, UserDto userDto) throws UserNotFoundException;
    void deleteUserByUsername(String username) throws UserNotFoundException;



    boolean isTokenValid (String token);
}
