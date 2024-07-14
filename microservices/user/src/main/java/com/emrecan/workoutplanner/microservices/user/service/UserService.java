package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.microservices.user.exceptions.UserAlreadyExistsException;
import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;


public interface UserService {

    UserDto createUser(UserDto user) throws UserAlreadyExistsException;
    boolean isValidUser(String username, String password);
}
