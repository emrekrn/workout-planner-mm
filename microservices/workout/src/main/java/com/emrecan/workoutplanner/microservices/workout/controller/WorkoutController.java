package com.emrecan.workoutplanner.microservices.workout.controller;

import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;
import com.emrecan.workoutplanner.microservices.workout.service.ExerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkoutController {

    ExerciseService exerciseService;

    public WorkoutController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/")
    public List<ExerciseDto> getAvailableExercises() {
        return exerciseService.getExercisesFromApiNinjas();
    }
}
