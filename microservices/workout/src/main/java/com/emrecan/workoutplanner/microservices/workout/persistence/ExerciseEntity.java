package com.emrecan.workoutplanner.microservices.workout.persistence;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = "workouts")
public class ExerciseEntity {

    @Id
    private String id;
    @Version
    private long version;

    @Indexed(unique = true)
    private Integer exerciseId;
    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
    private String instructions;
}
