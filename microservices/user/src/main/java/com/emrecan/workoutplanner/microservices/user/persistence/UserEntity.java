package com.emrecan.workoutplanner.microservices.user.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    @Version
    private Long version;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
