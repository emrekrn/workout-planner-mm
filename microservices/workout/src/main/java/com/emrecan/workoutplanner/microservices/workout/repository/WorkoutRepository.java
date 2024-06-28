package com.emrecan.workoutplanner.microservices.workout.repository;

import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends CrudRepository<WorkoutEntity, String> {
    List<WorkoutEntity> findAllByUserId(String userId);
}
