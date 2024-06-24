package com.emrecan.workoutplanner.microservices.user.repositories;

import com.emrecan.workoutplanner.microservices.user.persistance.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
