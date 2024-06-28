package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutDto;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {

    List<WorkoutDto> getAllWorkoutsByUserId(String userId);

    Optional<WorkoutDto> getWorkoutById(String workoutId);

    WorkoutDto createWorkout(WorkoutDto workoutDto);

    Optional<WorkoutDto> updateWorkout(WorkoutDto workoutDto);

    boolean deleteWorkout(String workoutId);
}
