package com.emrecan.workoutplanner.microservices.user.mapper;

import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;
import com.emrecan.workoutplanner.microservices.user.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {

    UserDto entityToApi(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "password", qualifiedByName = "encodePassword")
    })
    UserEntity apiToEntity(UserDto userDto);

    List<UserDto> entityListToApiList(List<UserEntity> entities);
}
