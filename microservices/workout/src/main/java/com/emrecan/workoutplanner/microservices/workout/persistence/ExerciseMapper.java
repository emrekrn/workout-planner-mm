package com.emrecan.workoutplanner.microservices.workout.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    @Mappings({
            @Mapping(target = "exerciseId"),
            @Mapping(target = "name"),
            @Mapping(target = "type"),
            @Mapping(target = "muscle"),
            @Mapping(target = "equipment"),
            @Mapping(target = "difficulty"),
            @Mapping(target = "instructions"),
    })
    ExerciseDto entityToApi(ExerciseEntity exerciseEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "exerciseId"),
            @Mapping(target = "name"),
            @Mapping(target = "type"),
            @Mapping(target = "muscle"),
            @Mapping(target = "equipment"),
            @Mapping(target = "difficulty"),
            @Mapping(target = "instructions"),
    })
    ExerciseEntity apiToEntity(ExerciseDto dto);

    List<ExerciseDto> entitiesToApis(List<ExerciseEntity> entities);
}
