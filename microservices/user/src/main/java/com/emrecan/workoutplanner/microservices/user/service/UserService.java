package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(String id);
    UserDto createUser(UserDto user);
    boolean isValidUser(String username, String password);
}
