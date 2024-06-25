package com.emrecan.workoutplanner.microservices.user.service;

import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;
import com.emrecan.workoutplanner.microservices.user.persistence.UserEntity;
import com.emrecan.workoutplanner.microservices.user.persistence.UserMapper;
import com.emrecan.workoutplanner.microservices.user.repositoriy.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        userRepository.findAll().forEach(userEntities::add);


        return mapper.entityListToApiList(userEntities);
    }

    @Override
    public UserDto getUserById(String id) {

        return mapper.entityToApi(userRepository.findById(id).get());
    }

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = mapper.apiToEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);

        return mapper.entityToApi(savedUser);
    }
}
