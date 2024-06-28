package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutDto;
import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutEntity;
import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutMapper;
import com.emrecan.workoutplanner.microservices.workout.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService{

    private WorkoutRepository workoutRepository;
    private WorkoutMapper workoutMapper;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    @Override
    public List<WorkoutDto> getAllWorkoutsByUserId(String userId) {
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        workoutRepository.findAllByUserId(userId).forEach(workoutEntities::add);
        return workoutMapper.entitiesToDtos(workoutEntities);
    }

    @Override
    public Optional<WorkoutDto> getWorkoutById(String id) {
        WorkoutEntity workoutEntity = workoutRepository.findById(id).orElse(null);
        if (workoutEntity != null) {
            return Optional.empty();
        }
        return Optional.of(workoutMapper.entityToDto(workoutEntity));
    }

    @Override
    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = workoutMapper.dtoToEntity(workoutDto);
        workoutEntity = workoutRepository.save(workoutEntity);
        return workoutMapper.entityToDto(workoutEntity);
    }

    @Override
    public Optional<WorkoutDto> updateWorkout(WorkoutDto workoutDto) {

        if (isPresent(workoutDto.getWorkoutId())) {
            WorkoutEntity workoutEntity = workoutMapper.dtoToEntity(workoutDto);
            workoutEntity = workoutRepository.save(workoutEntity);
            return Optional.of(workoutMapper.entityToDto(workoutEntity));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteWorkout(String workoutId) {
        if (isPresent(workoutId)) {
            workoutRepository.deleteById(workoutId);
            return true;
        }
        return false;
    }

    private boolean isPresent(String workoutId) {
        return workoutRepository.existsById(workoutId);
    }
}
