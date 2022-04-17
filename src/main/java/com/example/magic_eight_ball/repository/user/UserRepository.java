package com.example.magic_eight_ball.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.magic_eight_ball.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
}
