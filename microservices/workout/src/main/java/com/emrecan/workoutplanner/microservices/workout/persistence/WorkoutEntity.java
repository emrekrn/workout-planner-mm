package com.emrecan.workoutplanner.microservices.workout.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "workouts")
public class WorkoutEntity {

    @Id
    private String workoutId;
    @Version
    private long version;
    private String workoutName;
    private String workoutDescription;
    private String userId;

}
