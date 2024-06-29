package com.emrecan.workoutplanner.microservices.workout.persistence;

import lombok.Data;

@Data
public class WorkoutDto {

    private String workoutId;
    private String workoutName;
    private String workoutDescription;
    private String userId;
}
