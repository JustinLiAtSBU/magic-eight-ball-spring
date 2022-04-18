package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMoviesByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres);

    Movie getRandomMovie(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres);

    Movie getChannelsNonWatchedRandomMovie(String channelId, Integer top, String country, Double minRating, Integer minVotes, Integer minYear, List<String> genres);
}
