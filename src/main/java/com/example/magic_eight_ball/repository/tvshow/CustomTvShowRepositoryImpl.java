package com.example.magic_eight_ball.repository.tvshow;

import com.example.magic_eight_ball.model.TvShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomTvShowRepositoryImpl implements CustomTvShowRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<TvShow> getTvShowsByFields(Query query) {
        return mongoTemplate.find(query, TvShow.class);
    }
}