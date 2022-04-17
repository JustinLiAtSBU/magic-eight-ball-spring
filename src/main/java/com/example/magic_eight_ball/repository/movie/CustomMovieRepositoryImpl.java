package com.example.magic_eight_ball.repository.movie;

import com.example.magic_eight_ball.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

@Component
public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Movie> getMoviesByFields(Query query) {
        return mongoTemplate.find(query, Movie.class);
    }
}
