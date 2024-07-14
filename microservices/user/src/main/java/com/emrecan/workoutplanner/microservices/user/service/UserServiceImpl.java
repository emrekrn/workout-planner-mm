package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;
import com.emrecan.workoutplanner.microservices.user.persistence.UserEntity;
import com.emrecan.workoutplanner.microservices.user.mapper.UserMapper;
import com.emrecan.workoutplanner.microservices.user.repositoriy.UserRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = mapper.apiToEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return mapper.entityToApi(savedUser);
    }

    @Override
    public boolean isValidUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return !(userEntity == null) && checkPasswordsMatch(userEntity.getPassword(), password);
    }

    private boolean checkPasswordsMatch(String usersPassword, String enteredPassword) {
        return passwordEncoder.matches(enteredPassword, usersPassword);
    }
}
