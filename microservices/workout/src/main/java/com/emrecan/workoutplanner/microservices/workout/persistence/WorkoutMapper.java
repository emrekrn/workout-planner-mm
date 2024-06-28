package com.emrecan.workoutplanner.microservices.workout.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutDto entityToDto(WorkoutEntity workoutEntity);
    WorkoutEntity dtoToEntity(WorkoutDto workoutDto);
    List<WorkoutDto> entitiesToDtos(List<WorkoutEntity> workoutEntities);
    List<WorkoutEntity> dtosToEntities(List<WorkoutDto> workoutDtos);
}
