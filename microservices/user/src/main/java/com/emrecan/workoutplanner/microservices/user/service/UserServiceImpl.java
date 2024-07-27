package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.exceptions.UserConflictException;
import com.emrecan.workoutplanner.exceptions.UserNotFoundException;
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
    public UserDto getUserByUsername(String username) throws UserNotFoundException {
        if (doesUsernameExist(username)) {
            UserEntity userEntity = userRepository.findByUsername(username);
            return mapper.entityToApi(userEntity);
        }
        else {
            throw new UserNotFoundException("Username does not exist");
        }
    }

    @Override
    public UserDto createUser(UserDto user) throws UserConflictException {
        if (doesUsernameExist(user.getUsername()) || doesEmailExist(user.getEmail())) {
            throw new UserConflictException("Username or Email already exists. Username: " + user.getUsername() + " Email: " + user.getEmail());
        }
        UserEntity userEntity = mapper.apiToEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return mapper.entityToApi(savedUser);
    }

    @Override
    public boolean isValidUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return !(userEntity == null) && checkPasswordsMatch(userEntity.getPassword(), password);
    }

    @Override
    public UserDto updateUser(String username, UserDto user) throws UserNotFoundException {
        if (doesUsernameExist(username)) {
            UserEntity userEntity = userRepository.findByUsername(username);

            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntity.setEmail(user.getEmail());
            userEntity.setFirstname(user.getFirstname());
            userEntity.setLastname(user.getLastname());

            userRepository.save(userEntity);
            return mapper.entityToApi(userEntity);
        }
        else {
            throw new UserNotFoundException("Username does not exist");
        }

    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        if (doesUsernameExist(username)) {
            UserEntity userEntity = userRepository.findByUsername(username);
            userRepository.delete(userEntity);
        }
        else {
            throw new UserNotFoundException("Username does not exist");
        }
    }

    private boolean checkPasswordsMatch(String usersPassword, String enteredPassword) {
        return passwordEncoder.matches(enteredPassword, usersPassword);
    }

    private boolean doesUsernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private boolean doesEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
