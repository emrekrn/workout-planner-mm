package com.emrecan.workoutplanner.microservices.user.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "userId"),
            @Mapping(target = "username"),
            @Mapping(target = "password"),
            @Mapping(target = "email")
    })
    UserDto entityToApi(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "username"),
            @Mapping(target = "password"),
            @Mapping(target = "email")
    })
    UserEntity apiToEntity(UserDto userDto);

    List<UserDto> entityListToApiList(List<UserEntity> entities);

}
