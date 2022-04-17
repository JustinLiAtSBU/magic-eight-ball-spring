package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.repository.CustomTvShowRepository;
import com.example.magic_eight_ball.repository.TvShowRepository;
import com.example.magic_eight_ball.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TvShowServiceImpl implements TvShowService {

    @Autowired
    TvShowRepository tvShowRepository;

    @Autowired
    CustomTvShowRepository customTvShowRepository;

    @Override
    public List<TvShow> getTvShowsByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Query query = QueryBuilder.motionPictureQuery(top, iso, minRating, minVotes, minYear, genres, sort);
        return customTvShowRepository.getTvShowsByFields(query);
    }

    @Override
    public TvShow getRandomTvShow(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List<TvShow> tvShows = getTvShowsByCriteria(top, iso, minRating, minVotes, minYear, genres);
        int upperBound = tvShows.size();
        if (top != null && top <= tvShows.size()) {
            upperBound = top;
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return tvShows.get(randomNum);
    }
}
