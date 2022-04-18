package com.example.magic_eight_ball.repository.channel;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.magic_eight_ball.model.Channel;

import java.util.Optional;

public interface ChannelRepository extends MongoRepository<Channel, String> {
    Optional<Channel> findByChannelId(String channelId);
}
