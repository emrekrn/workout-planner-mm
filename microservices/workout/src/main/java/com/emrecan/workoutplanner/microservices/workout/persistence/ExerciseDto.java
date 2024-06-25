package com.emrecan.workoutplanner.microservices.workout.persistence;

import lombok.Data;

@Data
public class ExerciseDto {
    private Integer exerciseId;
    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
    private String instructions;
}
