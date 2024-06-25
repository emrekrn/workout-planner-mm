package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getExercisesFromApiNinjas();
}
