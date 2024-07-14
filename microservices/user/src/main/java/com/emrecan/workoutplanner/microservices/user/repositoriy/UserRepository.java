package com.emrecan.workoutplanner.microservices.user.repositoriy;

import com.emrecan.workoutplanner.microservices.user.persistence.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByUsername(String userName);
    UserEntity findByEmail(String email);
}
