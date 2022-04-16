package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.repository.CustomMovieRepository;
import com.example.magic_eight_ball.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CustomMovieRepository customMovieRepository;

    @Override
    public List<Movie> getMoviesByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List <Criteria> criteria = new ArrayList<>();
        if (iso != null) {
            Locale l = new Locale("", iso);
            String country = l.getDisplayCountry();
            criteria.add(Criteria.where("country").is(country));
        }
        if (minRating != null) {
            criteria.add(Criteria.where("rating").gte(minRating));
        }
        if (minVotes != null) {
            criteria.add(Criteria.where("votes").gte(minVotes));
        }
        if (minYear != null) {
            criteria.add(Criteria.where("year").gte(minYear));
        }
        if (genres != null) {
            criteria.add(Criteria.where("genres").all(genres));
        }

        Query query = new Query();
        if (!criteria.isEmpty()) {
            Criteria criterion = new Criteria();
            criterion.andOperator(criteria);
            query.addCriteria(criterion);
        }
        query.with(Sort.by(Sort.Order.desc("rating")));
        if (top != null) {
            query.limit(top);
        }

        return customMovieRepository.getMoviesByFields(query);
    }

    @Override
    public Movie getRandomMovie(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List<Movie> movies = getMoviesByCriteria(top, iso, minRating, minVotes, minYear, genres);
        int upperBound = movies.size();
        if (top != null && top <= movies.size()) {
            upperBound = top;
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return movies.get(randomNum);
    }
}
