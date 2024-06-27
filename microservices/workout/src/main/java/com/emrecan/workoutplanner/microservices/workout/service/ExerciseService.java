package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    /**
     * Get available exercises from API Ninjas based in url parameters
     * @param exerciseName Exercise name
     * @param exerciseType Exercise type
     * @param muscleGroup Muscle group
     * @param difficulty Difficulty of the exercise
     * @return found exersices as list
     */
    List<ExerciseDto> getExercisesFromApiNinjas(
        String exerciseName,
        String exerciseType,
        String muscleGroup,
        String difficulty
    );
}
