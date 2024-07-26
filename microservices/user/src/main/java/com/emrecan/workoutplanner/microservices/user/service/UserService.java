package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.exceptions.UserConflictException;
import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;


public interface UserService {

    UserDto createUser(UserDto user) throws UserConflictException;
    boolean isValidUser(String username, String password);
}
