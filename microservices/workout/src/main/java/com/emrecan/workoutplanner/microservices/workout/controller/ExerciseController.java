package com.emrecan.workoutplanner.microservices.workout.controller;

import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;
import com.emrecan.workoutplanner.microservices.workout.service.ExerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseController {

    ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public List<ExerciseDto> getAvailableExercises(
        @RequestParam(required = false) String exerciseName,
        @RequestParam(required = false) String exerciseType,
        @RequestParam(required = false) String muscleGroup,
        @RequestParam(required = false) String difficulty
    ) {
        return exerciseService.getExercisesFromApiNinjas(
            exerciseName,
            exerciseType,
            muscleGroup,
            difficulty
        );
    }

}
