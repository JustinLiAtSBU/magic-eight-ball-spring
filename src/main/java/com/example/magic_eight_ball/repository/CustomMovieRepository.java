package com.example.magic_eight_ball.repository;

import com.example.magic_eight_ball.model.Movie;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomMovieRepository {
    List<Movie> getMoviesByFields(Query query);
}
