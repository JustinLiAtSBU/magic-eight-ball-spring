package com.example.magic_eight_ball.repository.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomChannelRepositoryImpl implements CustomChannelRepository {

    @Autowired
    MongoTemplate mongoTemplate;
}
