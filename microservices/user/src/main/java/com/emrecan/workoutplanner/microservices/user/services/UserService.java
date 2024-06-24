package com.emrecan.workoutplanner.microservices.user.services;

import com.emrecan.workoutplanner.microservices.user.persistance.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(String id);
    UserDto createUser(UserDto user);
}
