package com.example.magic_eight_ball.repository;

import com.example.magic_eight_ball.model.TvShow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TvShowRepository extends MongoRepository<TvShow, String> {
    Optional<TvShow> findByTconst(String tconst);
}
