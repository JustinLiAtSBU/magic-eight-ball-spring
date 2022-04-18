package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.TvShow;

import java.util.List;

public interface TvShowService {
    List<TvShow> getTvShowsByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres);

    TvShow getRandomTvShow(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres);

    TvShow getChannelsNonWatchedRandomTvShow(String channelId, Integer top, String country, Double minRating, Integer minVotes, Integer minYear, List<String> genres);
}
