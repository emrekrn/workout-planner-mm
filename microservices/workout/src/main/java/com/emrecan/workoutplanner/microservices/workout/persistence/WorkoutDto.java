package com.emrecan.workoutplanner.microservices.workout.persistence;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutDto {

    private String workoutId;
    private String workoutName;
    private String workoutDescription;
    private LocalDate createdAt;
}
