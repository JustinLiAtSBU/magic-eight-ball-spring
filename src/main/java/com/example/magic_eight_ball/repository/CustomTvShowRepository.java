package com.example.magic_eight_ball.repository;

import com.example.magic_eight_ball.model.TvShow;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomTvShowRepository {
    List<TvShow> getTvShowsByFields(Query query);
}
