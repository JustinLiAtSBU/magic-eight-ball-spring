package com.example.magic_eight_ball.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    MongoTemplate mongoTemplate;
}
