package com.example.magic_eight_ball.repository.movie;

import com.example.magic_eight_ball.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByTconst(String tconst);
}
